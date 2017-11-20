package com.codeup.kidsync.controllers;

import com.codeup.kidsync.models.User;
import com.codeup.kidsync.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UsersController {
    private UsersRepository users;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsersController(UsersRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
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
        return "users/register";
    }

    @PostMapping("/teacher")
    public String saveTeacher(@ModelAttribute User user){
        user.setRole(1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        users.save(user);
        return "redirect:/login";
    }
}
