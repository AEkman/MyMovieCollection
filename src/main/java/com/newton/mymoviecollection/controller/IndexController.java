package com.newton.mymoviecollection.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/")
public class IndexController {

    @RequestMapping("/")
    public String index() {
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