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

    // Save new user to database
    @PostMapping(value = "/user")
    public String register(@RequestBody User user){
        return "User created";
    }

    // Get all users from database
    @GetMapping(value ="/user")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    // Get user by first name from database
    @GetMapping(value ="/user/{firstName}")
    public List<User> getUserByName(@RequestParam String firstName){
        return userService.getUserByFirstName(firstName);
    }
}