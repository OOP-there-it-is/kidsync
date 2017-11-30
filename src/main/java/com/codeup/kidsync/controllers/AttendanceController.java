package com.codeup.kidsync.controllers;

import com.codeup.kidsync.models.Attendance;
import com.codeup.kidsync.models.Grade;
import com.codeup.kidsync.repositories.GradesRepository;
import com.codeup.kidsync.services.AttendanceSvc;
import com.codeup.kidsync.services.StudentsSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AttendanceController {

    private final AttendanceSvc attendanceSvc;
    private final StudentsSvc studentsSvc;

    @Autowired
    public AttendanceController(AttendanceSvc attendanceSvc, StudentsSvc studentsSvc) {
        this.attendanceSvc = attendanceSvc;
        this.studentsSvc = studentsSvc;
    }

    @GetMapping("/attendance/add")
    public String AddAttendance(Model vModel) {
        vModel.addAttribute("attendance", new Attendance());
        vModel.addAttribute("students", studentsSvc.findAll());
        return "attendance/add";
    }

    @PostMapping("/attendance/add")
    public String AddAttendance(@ModelAttribute Attendance attendance) {
        attendanceSvc.save(attendance);
        return "users/homePage";
    }
}
