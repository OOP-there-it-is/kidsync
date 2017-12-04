package com.codeup.kidsync.controllers;

import com.codeup.kidsync.models.Attendance;
import com.codeup.kidsync.models.Grade;
import com.codeup.kidsync.models.Student;
import com.codeup.kidsync.models.User;
import com.codeup.kidsync.repositories.GradesRepository;
import com.codeup.kidsync.services.AttendanceSvc;
import com.codeup.kidsync.services.ClassSvc;
import com.codeup.kidsync.services.StudentsSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/attendance/add/{id}")
    public String AddAttendance(@PathVariable long id, Model vModel, HttpServletRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user.getRole() != 1) {
            return "errors/unauthorized";
        }
        Student student = studentsSvc.findOne(id);
        request.getSession().setAttribute("student", student);

        vModel.addAttribute("attendance", new Attendance());
        vModel.addAttribute("student", student);
        vModel.addAttribute("classes", classSvc.findClassByTeacher(user.getId()));
        return "attendance/add";
    }

    @PostMapping("/attendance/add")
    public String AddAttendance(@ModelAttribute Attendance attendance, HttpServletRequest request) {
        Student student = (Student)request.getSession().getAttribute("student");


        attendance.setStudent(student);


        attendanceSvc.save(attendance);

        return "redirect:/attendance/view/" + student.getId();
    }

    @GetMapping("/attendance/view/{id}")
    public String viewAttendance(@PathVariable long id, Model vModel, HttpServletRequest request) {

        User user = (User)request.getSession().getAttribute("user");

        vModel.addAttribute("user", user);
        vModel.addAttribute("student", studentsSvc.findOne(id));
        vModel.addAttribute("attendances", attendanceSvc.getAttendanceByStudent(id));
        return "attendance/view";
    }
}
