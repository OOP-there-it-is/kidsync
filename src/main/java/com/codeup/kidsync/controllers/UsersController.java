package com.codeup.kidsync.controllers;

import com.codeup.kidsync.models.User;
import com.codeup.kidsync.repositories.StudentsRepository;
import com.codeup.kidsync.repositories.UsersRepository;
import com.codeup.kidsync.services.StudentsSvc;
import com.codeup.kidsync.twillio.CheckCode;
import com.codeup.kidsync.twillio.SendSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


@Controller
public class UsersController {
    private UsersRepository users;
    private PasswordEncoder passwordEncoder;



    @Autowired
    public UsersController(UsersRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/home")
    public String yourPage(Model vModel){
        vModel.addAttribute("users", users.findAll());
        return "users/homePage";
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
