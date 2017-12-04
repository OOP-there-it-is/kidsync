package com.codeup.kidsync.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/")
//    @ResponseBody
    public String welcome(){
        return "index";
    }

    @GetMapping("/aboutUs")
    public String AboutUs(){
        return "about";
    }

}