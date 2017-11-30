package com.codeup.kidsync.controllers;

import com.codeup.kidsync.models.Attendance;
import com.codeup.kidsync.models.Grade;
import com.codeup.kidsync.models.User;
import com.codeup.kidsync.repositories.GradesRepository;
import com.codeup.kidsync.services.AttendanceSvc;
import com.codeup.kidsync.services.ClassSvc;
import com.codeup.kidsync.services.StudentsSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AttendanceController {

    private final AttendanceSvc attendanceSvc;
    private final StudentsSvc studentsSvc;
    private final ClassSvc classSvc;


    @Autowired
    public AttendanceController(AttendanceSvc attendanceSvc, StudentsSvc studentsSvc, ClassSvc classSvc) {
        this.attendanceSvc = attendanceSvc;
        this.studentsSvc = studentsSvc;
        this.classSvc = classSvc;
    }

    @GetMapping("/attendance/add")
    public String AddAttendance(Model vModel) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user.getRole() != 1) {
            return "errors/unauthorized";
        }
        vModel.addAttribute("attendance", new Attendance());
        vModel.addAttribute("students", studentsSvc.findAll());
        vModel.addAttribute("classes", classSvc.findClassByTeacher(user.getId()));
        return "attendance/add";
    }

    @PostMapping("/attendance/add")
    public String AddAttendance(@ModelAttribute Attendance attendance) {
        attendanceSvc.save(attendance);
        return "users/homePage";
    }
}
