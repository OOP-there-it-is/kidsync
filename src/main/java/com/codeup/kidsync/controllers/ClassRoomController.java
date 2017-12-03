package com.codeup.kidsync.controllers;

import com.codeup.kidsync.models.ClassRoom;
import com.codeup.kidsync.models.User;
import com.codeup.kidsync.repositories.ClassRepository;
import com.codeup.kidsync.repositories.UsersRepository;
import com.codeup.kidsync.services.ClassSvc;
import com.codeup.kidsync.services.StudentsSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Transactional
@Controller
public class ClassRoomController {

    private final UsersRepository usersDoa;
    private final ClassSvc classSvc;
    private final ClassRepository classRepository;
    private final StudentsSvc studentsSvc;





    @Autowired
    public ClassRoomController(UsersRepository usersDoa, ClassSvc classSvc, ClassRepository classRepository, StudentsSvc studentsSvc) {
        this.usersDoa = usersDoa;
        this.classSvc = classSvc;
        this.classRepository = classRepository;
        this.studentsSvc = studentsSvc;
    }

    @GetMapping("/classRoom/create")
    public String CreateClass(Model vModel, HttpServletRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user.getRole() != 1){
            return "errors/unauthorized";
        } else {

            vModel.addAttribute("class", new ClassRoom());
            vModel.addAttribute("teacher", user);
            request.getSession().setAttribute("user", user);
            return "classRoom/create";
        }
    }

    @PostMapping("/classRoom/create") //
    public String AddGrade(@ModelAttribute ClassRoom classroom, HttpServletRequest request) {
        User teacher = (User) request.getSession().getAttribute("user");
        classroom.setUser(teacher);
        classroom.setActive(true);
        classSvc.save(classroom);
        return "redirect:/home";
    }



    @GetMapping("/classRoom/myClasses/{id}")
    public String showClasses(@PathVariable long id, Model vModel, HttpServletRequest request) {
        User user = usersDoa.findOne(id);
        if(user.getRole() != 1){
            return "errors/unauthorized";
        } else {

            request.getSession().setAttribute("user", user);
            vModel.addAttribute("classes", classSvc.findClassByTeacher(user.getId()) );
            return "classRoom/myClasses";
        }
    }

    @GetMapping("/classRoom/view/{id}")
    public String viewClasses(@PathVariable long id, Model vModel, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if(user.getRole() != 1){
            return "errors/unauthorized";
        } else {
            ClassRoom classroom = classRepository.findOne(id);
            vModel.addAttribute("classroom", classroom);
            request.getSession().setAttribute("classroom",classroom);
            vModel.addAttribute("students", studentsSvc.getStudentsByClassId(id));
            return "classRoom/view";
        }
    }

    @PostMapping("/classRoom/view")
    public String dropClassroom( HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if(user.getRole() != 1){
            return "errors/unauthorized";
        } else {
           ClassRoom classroom = (ClassRoom) request.getSession().getAttribute("classroom");

            classroom.setActive(false);
            classSvc.save(classroom);

            return "redirect:/teacher-dash";
        }
    }

}
