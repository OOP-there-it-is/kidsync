package com.codeup.kidsync.controllers;

import com.codeup.kidsync.models.Class;
import com.codeup.kidsync.models.User;
import com.codeup.kidsync.repositories.ClassRepository;
import com.codeup.kidsync.repositories.UsersRepository;
import com.codeup.kidsync.services.ClassSvc;
import com.codeup.kidsync.services.StudentsSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class ClassController {

    private final UsersRepository usersDoa;
    private final ClassSvc classSvc;
    private final ClassRepository classRepository;
    private final StudentsSvc studentsSvc;





    @Autowired
    public ClassController(UsersRepository usersDoa, ClassSvc classSvc, ClassRepository classRepository, StudentsSvc studentsSvc) {
        this.usersDoa = usersDoa;
        this.classSvc = classSvc;
        this.classRepository = classRepository;
        this.studentsSvc = studentsSvc;
    }

    @GetMapping("/class/create/{email}")
    public String CreateClass(@PathVariable String email, Model vModel, HttpServletRequest request) {
        User user = usersDoa.findByEmail(email);
        if(user.getRole() != 1){
            return "errors/unauthorized";
        } else {

            vModel.addAttribute("class", new Class());
            vModel.addAttribute("teacher", usersDoa.findByEmail(email));
            request.getSession().setAttribute("user", user);
            return "class/create";
        }
    }

    @PostMapping("/class/create") //
    public String AddGrade(@ModelAttribute Class classroom, HttpServletRequest request) {
        User teacher = (User) request.getSession().getAttribute("user");
        classroom.setUser(teacher);
        classSvc.save(classroom);
        return "users/homePage";
    }



    @GetMapping("/class/myClasses/{email}")
    public String showClasses(@PathVariable String email, Model vModel, HttpServletRequest request) {
        User user = usersDoa.findByEmail(email);
        if(user.getRole() != 1){
            return "errors/unauthorized";
        } else {

            request.getSession().setAttribute("user", user);
            vModel.addAttribute("classes", classSvc.findClassByTeacher(user.getId()));
            return "class/myClasses";
        }
    }

    @GetMapping("/class/view/{id}")
    public String viewClasses(@PathVariable long id, Model vModel, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if(user.getRole() != 1){
            return "errors/unauthorized";
        } else {
            long num = 2;
            vModel.addAttribute("classroom", classRepository.findOne(id));
            vModel.addAttribute("students", studentsSvc.findAll());
//
            return "class/view";
        }
    }


}
