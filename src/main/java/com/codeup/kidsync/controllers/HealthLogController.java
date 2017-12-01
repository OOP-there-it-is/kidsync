package com.codeup.kidsync.controllers;

import com.codeup.kidsync.models.HealthLog;
import com.codeup.kidsync.models.Student;
import com.codeup.kidsync.models.User;
import com.codeup.kidsync.services.HealthLogSvc;
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
public class HealthLogController {

    private final HealthLogSvc healthLogSvc;
    private final StudentsSvc studentsSvc;

    @Autowired
    public HealthLogController(HealthLogSvc healthLogSvc, StudentsSvc studentsSvc){
        this.healthLogSvc = healthLogSvc;
        this.studentsSvc = studentsSvc;

    }

    @GetMapping("/healthLog/add/{id}")
    public String AddLog(@PathVariable long id, Model vModel, HttpServletRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user.getRole() != 1) {
            return "errors/unauthorized";
        }

        Student student = studentsSvc.findOne(id);
        request.getSession().setAttribute("student", student);

        vModel.addAttribute("healthLog", new HealthLog());
        vModel.addAttribute("student", student);
        return "healthLog/add";
    }

    @PostMapping("/healthLog/add")
    public String AddHealthLog(@ModelAttribute HealthLog log, HttpServletRequest request) {
        Student student = (Student) request.getSession().getAttribute("student");
        log.setStudent(student);
        healthLogSvc.save(log);
        return "redirect:/teacher-dash";
    }
}
