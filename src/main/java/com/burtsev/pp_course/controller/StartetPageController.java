package com.burtsev.pp_course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class StartetPageController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/auth")
    public String pageForAuthUsers(Principal principal){
        return "auth: " + principal.getName();
    }
}
