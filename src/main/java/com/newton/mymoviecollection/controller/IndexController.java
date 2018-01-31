package com.newton.mymoviecollection.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {

    @GetMapping(value="/")
    public String index(){
        return "index";
    }

    @GetMapping(value="/register")
    public String register(){
        return "register";
    }

    @GetMapping(value="/login")
    public String login(){
        return "login";
    }

    @GetMapping(value="/logout")
    public String logout(){
        return "logout";
    }

    @GetMapping(value = "/admin")
    public String adminArea() {
        return "Admin only";
    }
}
