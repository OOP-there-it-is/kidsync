package com.codeup.kidsync.controllers;

import com.codeup.kidsync.models.Student;
import com.codeup.kidsync.models.User;
import com.codeup.kidsync.repositories.StudentsRepository;
import com.codeup.kidsync.services.StudentsSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class StudentsController {

    private final StudentsSvc studentsSvc;
    private final StudentsRepository studentsDoa;

    @Autowired
    public StudentsController(StudentsSvc studentsSvc, StudentsRepository studentsDoa){
        this.studentsSvc = studentsSvc;
        this.studentsDoa = studentsDoa;
    }

    @GetMapping("/students")
    public String showAll(Model vModel) {
        vModel.addAttribute("students", studentsSvc.findAll());
        vModel.addAttribute("users", studentsDoa.findAll());
        return "students/view";
    }
    @GetMapping("/students/{id}")
    public String singlePost(@PathVariable int id, Model vModel) {
        Student student = new Student();
        vModel.addAttribute("student", studentsSvc.findOne(id));
        return "students/view";
    }

    @GetMapping("/students/add")
    public String AddChild(Model vModel) {
        vModel.addAttribute("student", new Student());
        return "students/add";
    }

    @PostMapping("/students/add") //
    public String AddChild(@ModelAttribute Student student) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        student.setUser(user);
        studentsSvc.save(student);
        return "users/homePage";
    }

}

