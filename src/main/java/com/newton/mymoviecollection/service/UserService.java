package com.newton.mymoviecollection.service;

import com.newton.mymoviecollection.entity.User;
import com.newton.mymoviecollection.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Save user to database
    public void save(User user){
        userRepository.save(user);
    }

    // Get all users from database
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get users by firstName database
    public List<User> getUserByFirstName(String firstName) {
        return userRepository.findByUsername(firstName);
    }

    public List<User> getAllUserMovies() {
        return userRepository.findAll();
    }
}
