package com.codeup.kidsync.controllers;

import com.codeup.kidsync.models.ClassRoom;
import com.codeup.kidsync.models.Student;
import com.codeup.kidsync.models.User;
import com.codeup.kidsync.repositories.ClassRepository;
import com.codeup.kidsync.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class StudentsController {

    private final StudentsSvc studentsSvc;
    private final ClassRepository classRepository;
    private final GradesSvc gradesSvc;
    private final AttendanceSvc attendanceSvc;
    private final ClassSvc classSvc;
    private final HealthLogSvc healthLogSvc;

    @Autowired
    public StudentsController(StudentsSvc studentsSvc, ClassRepository classRepository, GradesSvc gradesSvc, AttendanceSvc attendanceSvc, ClassSvc classSvc, HealthLogSvc healthLogSvc){
        this.studentsSvc = studentsSvc;
        this.classRepository = classRepository;
        this.gradesSvc= gradesSvc;
        this.attendanceSvc = attendanceSvc;
        this.classSvc = classSvc;
        this.healthLogSvc = healthLogSvc;
    }

    @GetMapping("/mystudents/{id}")
    public String showAll(@PathVariable long id, Model vModel) {
        vModel.addAttribute("students", studentsSvc.getStudentsByUserId(id));
        studentsSvc.getStudentsByUserId(id);
        vModel.addAttribute("classroom", classSvc.findAll());
        return "students/view";
    }

    @GetMapping("/students/add")
    public String AddChild(Model vModel) {
        vModel.addAttribute("student", new Student());

        List<ClassRoom> activeClasses = (List<ClassRoom>) classSvc.findAll();


        for(int i = 0; i < activeClasses.size(); i++){
            if(!activeClasses.get(i).isActive()){
                activeClasses.remove(i);
            }
            vModel.addAttribute("classrooms", activeClasses);
        }
        vModel.addAttribute("classrooms", activeClasses);
        return "students/add";
    }

    @PostMapping("/students/add")
    public String AddChild(@ModelAttribute Student student, HttpServletRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        student.setUser(user);
        long classId = Long.parseLong(request.getParameter("classroom"));
        student.setClassroom(classRepository.findOne(classId));
        studentsSvc.save(student);
        return "redirect:/home";
    }

    @GetMapping("/dash/{id}")
    public String ShowStudent(@PathVariable long id, Model vModel) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//        vModel.addAttribute("student", studentsSvc.findOne(id));
        vModel.addAttribute("students", studentsSvc.getStudentsByUserId(user.getId()));
        vModel.addAttribute("grades", gradesSvc.getGradesByStudent(id));
        vModel.addAttribute("attendance", attendanceSvc.getAttendanceByStudent(id));
        vModel.addAttribute("healthlog", healthLogSvc.getHealthLogByStudent(id));

        return "students/dash";
    }

    @GetMapping("/mystudent/edit/{id}")
    public String EditClass(@PathVariable long id, Model vModel, HttpServletRequest request) {
        vModel.addAttribute("student", studentsSvc.findOne(id));

       request.getSession().setAttribute("student", studentsSvc.findOne(id));

        List<ClassRoom> activeClasses = (List<ClassRoom>) classSvc.findAll();


        for(int i = 0; i < activeClasses.size(); i++){
            if(!activeClasses.get(i).isActive()){
                activeClasses.remove(i);
            }
            vModel.addAttribute("classrooms", activeClasses);
        }

        vModel.addAttribute("classrooms", activeClasses);
        return "students/edit";
    }

    @PostMapping("/students/edit")
    public String EditClass(HttpServletRequest request) {

        Student student = (Student) request.getSession().getAttribute("student");

        long classId = Long.parseLong(request.getParameter("classroom"));
        student.setClassroom(classRepository.findOne(classId));
        studentsSvc.save(student);
        return "redirect:/home";
    }

}

