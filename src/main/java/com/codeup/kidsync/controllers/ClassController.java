package com.codeup.kidsync.controllers;

import com.codeup.kidsync.models.Class;
import com.codeup.kidsync.models.User;
import com.codeup.kidsync.repositories.UsersRepository;
import com.codeup.kidsync.services.ClassSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ClassController {

    private final UsersRepository usersDoa;
    private final ClassSvc classSvc;



    @Autowired
    public ClassController(UsersRepository usersDoa, ClassSvc classSvc) {
        this.usersDoa = usersDoa;
        this.classSvc = classSvc;
    }

    @GetMapping("/class/create")
    public String AddGrade(Model vModel) {
        vModel.addAttribute("class", new Class());
        vModel.addAttribute("teacher", usersDoa.findByEmail("n"));
        System.out.println(usersDoa.findByEmail("n").getF_name());
        return "class/create";
    }

    @PostMapping("/class/create") //
    public String AddGrade(@ModelAttribute Class classroom) {
        User teacher = usersDoa.findByEmail("n");
        classroom.setUser(teacher);
        classSvc.save(classroom);
        return "users/homePage";
    }
}
