package com.codeup.kidsync.controllers;

import com.codeup.kidsync.models.ClassRoom;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class GradesController {

    private final GradesSvc gradesSvc;
    private final StudentsSvc studentsSvc;
    private final ClassSvc classSvc;
    private final ClassRepository classRepository;

    @Autowired
    public GradesController(GradesSvc gradesSvc, StudentsSvc studentsSvc, ClassRepository classRepository, ClassSvc classSvc){
        this.gradesSvc = gradesSvc;
        this.studentsSvc = studentsSvc;
        this.classRepository = classRepository;
        this.classSvc = classSvc;

    }

    @GetMapping("/grades/add/{id}")
    public String AddGrade(Model vModel, HttpServletRequest request, @PathVariable long id) {
        User user = (User) request.getSession().getAttribute("user");
        if(user.getRole() != 1) {
            return "errors/unauthorized";
        }

        Student student = studentsSvc.findOne(id);
         request.getSession().setAttribute("student", student);

        vModel.addAttribute("grade", new Grade());
        vModel.addAttribute("student", studentsSvc.findOne(id));

        return "grades/add";
    }

    @PostMapping("/grades/add")
    public String AddGrade(@ModelAttribute Grade grade, HttpServletRequest request) {
        Student student = (Student) request.getSession().getAttribute("student");
        grade.setStudent(student);
        gradesSvc.save(grade);
        return "redirect:/grades/view/" + student.getId();
    }

    @GetMapping("/grades/view/{id}")
    public String ViewAll(Model vModel, @PathVariable long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        vModel.addAttribute("grades", gradesSvc.getGradesByStudent(id));
        vModel.addAttribute("student", studentsSvc.findOne(id));

        return "grades/view";
    }
}
