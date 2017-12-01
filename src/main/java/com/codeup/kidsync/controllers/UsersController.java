package com.codeup.kidsync.controllers;

import com.codeup.kidsync.models.ClassRoom;
import com.codeup.kidsync.models.User;
import com.codeup.kidsync.repositories.StudentsRepository;
import com.codeup.kidsync.repositories.UsersRepository;
import com.codeup.kidsync.services.ClassSvc;
import com.codeup.kidsync.services.StudentsSvc;
import com.codeup.kidsync.twillio.CheckCode;
import com.codeup.kidsync.twillio.SendSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UsersController {
    private UsersRepository users;
    private PasswordEncoder passwordEncoder;
    private final StudentsSvc studentsSvc;
    private final ClassSvc classSvc;



    @Autowired
    public UsersController(UsersRepository users, PasswordEncoder passwordEncoder, StudentsSvc studentsSvc, ClassSvc classSvc) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.studentsSvc = studentsSvc;
        this.classSvc = classSvc;
    }

    @GetMapping("/home")
    public String parentHome(Model vModel, HttpServletRequest request){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.getSession().setAttribute("user", user);
        if(user.getRole() ==1){
            return "redirect:/teacher-dash";
        }
        vModel.addAttribute("users", users.findAll());
        vModel.addAttribute("students", studentsSvc.getStudentsByUserId(user.getId()));
        return "users/homePage";
    }

    @GetMapping("/teacher-dash")
    public String teacherHome(Model vModel, HttpServletRequest request){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user.getRole() != 1) {
            return "errors/unauthorized";
        }
        request.getSession().setAttribute("user", user);
        List<ClassRoom> classRooms = classSvc.findClassByTeacher(user.getId());

        vModel.addAttribute("user", user);
        vModel.addAttribute("classrooms", classRooms);
        return "users/teacher-homePage";
    }

    @GetMapping("/register")
    public String showParentRegister(Model model){
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String saveParent(@ModelAttribute User user){
        user.setRole(2);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        users.save(user);
        return "redirect:/login";
    }

    @GetMapping("/teacher")
    public String showTeacherRegister(Model model){
        model.addAttribute("user", new User());
        return "users/teacher";
    }

    @PostMapping("/teacher")
    public String saveTeacher(@ModelAttribute User user){
        user.setRole(1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        users.save(user);
        return "redirect:/login";
    }

    @GetMapping("/signUp")
    public String showSignUp(Model model){
        model.addAttribute("user", new User());
        return "users/signUp";
    }


    @GetMapping("/invite")
    public String showInviteForm(Model vModel){
        vModel.addAttribute("user", users.findAll());
        return "users/invite";
    }



    @PostMapping("/invite")
    public String enterCode(HttpServletRequest request) {
        String code = request.getParameter("verify");
        CheckCode checkCode = new CheckCode();
        if(!checkCode.goodCode(code)){
            return "users/invite";
        }
        return "redirect:/register";
    }

    @GetMapping("/sms")
    public String showForm(Model vModel){
        vModel.addAttribute("user", users.findAll());
        return "users/sms";
    }

    @PostMapping("/sms")
    public String sendSms(HttpServletRequest request){
        String phone = request.getParameter("phone");
        SendSms send = new SendSms();
        send.sendCode(phone);
        //add parent to class(parent)
        return "redirect:/invite";
    }
}
