package com.codeup.kidsync.controllers;

import com.codeup.kidsync.models.Grade;
import com.codeup.kidsync.models.Student;
import com.codeup.kidsync.models.User;
import com.codeup.kidsync.repositories.ClassRepository;
import com.codeup.kidsync.repositories.GradesRepository;
import com.codeup.kidsync.repositories.StudentsRepository;
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
public class GradesController {

    private final GradesSvc gradesSvc;
    private final StudentsSvc studentsSvc;
//    private final ClassSvc classSvc;
    private final ClassRepository classRepository;

    @Autowired
    public GradesController(GradesSvc gradesSvc, StudentsSvc studentsSvc, ClassRepository classRepository){
        this.gradesSvc = gradesSvc;
        this.studentsSvc = studentsSvc;
        this.classRepository = classRepository;

    }

    @GetMapping("/grades/add/{id}")
    public String AddGrade(@PathVariable long id, Model vModel, HttpServletRequest request) {
        vModel.addAttribute("grade", new Grade());
        vModel.addAttribute("student", studentsSvc.findOne(id));
        Student student = studentsSvc.findOne(id);
        request.getSession().setAttribute("student", student);

        return "grades/add";
    }

    @PostMapping("/grades/add")
    public String AddGrade(@ModelAttribute Grade grade, HttpServletRequest request) {
        Student student = (Student) request.getSession().getAttribute("student");
        grade.setStudent(student);
        gradesSvc.save(grade);
        return "users/homePage";
    }
}
