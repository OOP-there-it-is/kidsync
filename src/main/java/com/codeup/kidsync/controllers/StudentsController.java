package com.codeup.kidsync.controllers;

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
        vModel.addAttribute("classrooms",classSvc.findAll());
        return "students/dash";
    }

    @PostMapping("/students/add")
    public String AddChild(@ModelAttribute Student student, HttpServletRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        student.setUser(user);
        long classId = Long.parseLong(request.getParameter("classroom"));
        student.setClassroom(classRepository.findOne(classId));
        studentsSvc.save(student);
        return "users/homePage";
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

}

