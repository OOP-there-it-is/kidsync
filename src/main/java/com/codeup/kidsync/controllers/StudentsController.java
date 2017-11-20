//package com.codeup.kidsync.controllers;
//
//
//import com.codeup.kidsync.repositories.StudentsRepository;
//import com.codeup.kidsync.services.StudentsSvc;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//public class StudentsController {
//
//    private final StudentsSvc studentsSvc;
//    private final StudentsRepository studentsDoa;
//
//
//    @Autowired
//    public StudentsController(StudentsSvc studentsSvc, StudentsRepository studentsDoa){
//        this.studentsSvc = studentsSvc;
//        this.studentsDoa = studentsDoa;
//    }
//
////    @GetMapping("/posts")
////    public String showAll(Model vModel) {
////        vModel.addAttribute("posts", postSvc.findAll());
////        vModel.addAttribute("users", userDao.findAll());
////        return "posts/index";
////    }
////    @GetMapping("/posts/{id}")
////    public String singlePost(@PathVariable int id, Model vModel) {
////        Post post = new Post("Example 1", "Body goes here");
////        vModel.addAttribute("post", postSvc.findOne(id));
////        return "posts/show";
////    }
////    @GetMapping("/posts/create")
////    public String ViewCreateForm(Model vModel) {
////        vModel.addAttribute("post", new Post());
////        return "posts/create";
////    }
////
////
////    @PostMapping("/posts/create") //
////    public String CreatePost(@ModelAttribute Post post) {
////        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////        post.setUser(user);
////        postSvc.save(post);
////        return "redirect:/posts";
////    }
////
////    @GetMapping("/posts/{id}/edit")
////    public String editPost(@PathVariable int id, Model vModel) {
////        vModel.addAttribute("post", postSvc.findOne(id));
////        return "posts/edit";
////    }
////
////    @PostMapping("/posts/{id}/edit")
////    public String editPost(@PathVariable int id, @ModelAttribute Post post) {
////        postSvc.save(post);
////        return "redirect:/posts";
////    }
////
////    @GetMapping("/posts/{id}/delete")
////    public String deletePost(@PathVariable int id, Model vModel) {
////        vModel.addAttribute("post", postSvc.findOne(id));
////        return "posts/delete";
////    }
////
////    @PostMapping("/posts/{id}/delete")
////    public String deletePost(@PathVariable int id) {
////        postSvc.deletePost(id);
////        return "redirect:/posts";
////    }
//
//}
//
