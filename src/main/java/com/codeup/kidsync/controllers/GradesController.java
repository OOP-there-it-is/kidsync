package com.codeup.kidsync.controllers;

import com.codeup.kidsync.models.Grade;
import com.codeup.kidsync.models.Student;
import com.codeup.kidsync.models.User;
import com.codeup.kidsync.repositories.GradesRepository;
import com.codeup.kidsync.repositories.StudentsRepository;
import com.codeup.kidsync.services.GradesSvc;
import com.codeup.kidsync.services.StudentsSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GradesController {

    private final GradesSvc gradesSvc;
    private final GradesRepository gradesDoa;
    private final StudentsSvc studentsSvc;

    @Autowired
    public GradesController(GradesSvc gradesSvc, StudentsSvc studentsSvc, GradesRepository gradesDoa){
        this.gradesSvc = gradesSvc;
        this.gradesDoa = gradesDoa;
        this.studentsSvc = studentsSvc;
    }

    @GetMapping("/grades/addGrade")
    public String AddGrade(Model vModel) {
        vModel.addAttribute("grade", new Grade());
        vModel.addAttribute("students", studentsSvc.findAll());
        return "grades/addGrade";
    }

    @PostMapping("/grades/addGrade") //
    public String AddGrade(@ModelAttribute Grade grade) {
        gradesSvc.save(grade);
        return "users/homePage";
    }
}
