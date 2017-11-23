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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class GradesController {

    private final GradesSvc gradesSvc;
    private final GradesRepository gradesDoa;

    @Autowired
    public GradesController(GradesSvc gradesSvc, GradesRepository gradesDoa){
        this.gradesSvc = gradesSvc;
        this.gradesDoa = gradesDoa;
    }

    @GetMapping("/grades/add")
    public String AddGrade(Model vModel) {
        vModel.addAttribute("grade", new Grade());
        return "students/add";
    }

    @PostMapping("/grades/add") //
    public String AddGrade(@ModelAttribute Grade grade) {
        Student student = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        grade.setStudent(student);
        gradesSvc.save(grade);
        return "users/homePage";
    }
}
