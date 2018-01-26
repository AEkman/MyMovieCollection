package com.newton.mymoviecollection.controller;

import com.newton.mymoviecollection.entity.User;
import com.newton.mymoviecollection.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/register")
    public String register(@RequestBody User user){

        return "User created";
    }

    @GetMapping(value = "/users")
    public List<User> users(){
        return userService.getAllUsers();
    }

    @GetMapping(value = "/logout")
    public void logout(@RequestParam(value = "access_token") String accessToken){

    }

    @GetMapping(value ="/getUsername")
    public void getUsername(){

    }
}