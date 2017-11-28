package com.codeup.kidsync.controllers;

import com.codeup.kidsync.models.Class;
import com.codeup.kidsync.models.Student;
import com.codeup.kidsync.models.User;
import com.codeup.kidsync.repositories.UsersRepository;
import com.codeup.kidsync.services.AttendanceSvc;
import com.codeup.kidsync.services.ClassSvc;
import com.codeup.kidsync.services.GradesSvc;
import com.codeup.kidsync.services.StudentsSvc;
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
    private final UsersRepository usersDoa;
    private final GradesSvc gradesSvc;
    private final AttendanceSvc attendanceSvc;
    private final ClassSvc classSvc;

    @Autowired
    public StudentsController(StudentsSvc studentsSvc, UsersRepository usersDoa, GradesSvc gradesSvc, AttendanceSvc attendanceSvc, ClassSvc classSvc){
        this.studentsSvc = studentsSvc;
        this.usersDoa = usersDoa;
        this.gradesSvc= gradesSvc;
        this.attendanceSvc = attendanceSvc;
        this.classSvc = classSvc;
    }

    @GetMapping("/mystudents/{id}")
    public String showAll(@PathVariable long id, Model vModel) {
        vModel.addAttribute("students", studentsSvc.getStudentsByUserId(id));
        return "students/view";
    }

    @GetMapping("/students/add")
    public String AddChild(Model vModel) {
        vModel.addAttribute("student", new Student());
        vModel.addAttribute("classrooms",classSvc.findAll());
        System.out.println(classSvc.findAll());
        return "students/add";
    }

    @PostMapping("/students/add")
    public String AddChild(@ModelAttribute Student student) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        student.setUser(user);
        studentsSvc.save(student);
        return "users/homePage";
    }

    @GetMapping("/dash/{id}")
    public String ShowStudent(@PathVariable long id, Model vModel) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        vModel.addAttribute("student", studentsSvc.findOne(id));
        vModel.addAttribute("grades", gradesSvc.getGradesByStudent(id));
        vModel.addAttribute("attendance", attendanceSvc.getAttendanceByStudent(id));

        return "students/dash";
    }

}

