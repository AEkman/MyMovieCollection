package com.newton.mymoviecollection.controller;

import com.newton.mymoviecollection.entity.Movie;
import com.newton.mymoviecollection.entity.User;
import com.newton.mymoviecollection.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


/* User - GetMappings */


    // Get all users from database
    @GetMapping(value ="/user")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    // Get user by id from database
    @GetMapping(value ="/user/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    // Get user by username from database
    @GetMapping(value ="/user/username/{username}")
    public User getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    // Get movies by user
    @GetMapping(value ="/user/{id}/movies")
    public List<Movie> getUserMovies(@PathVariable Long id){
        return userService.getMoviesByUserId(id);
    }


/* User - PostMappings */


    // Save new user to database
    @PostMapping(value = "/user")
    public String registerUser(@RequestBody User user){
        userService.saveUser(user);
        return "User created with id = " + user.getId() + " and username = " + user.getUsername();
    }


/* User - PutMappings */


    // Update user in database
    @PutMapping(value = "/user")
    public String updateUser(@RequestBody User user){
        userService.updateUser(user);
        return "User updated";
    }

    // Add movie to user in database
    @PutMapping(value = "/user/{user}/add/movie")
    public String updateUserAddMovie(@PathVariable User user, @RequestBody Movie movie){
        userService.updateUserAddMovie(user, movie);

        return "Movie " + movie.getTitle() + " got added to user " + user.getUsername();
    }


/* User - DeleteMappings */


    // Delete movie from user in database
    @PutMapping(value = "/user/{id}/delete/movie/{imdbId}")
    public String updateUserDeleteMovie(@PathVariable User id, @PathVariable String imdbId){
        userService.updateUserDeleteMovie(id, imdbId);
        return "Movie with imdb id " + imdbId + " deleted from user " + id.getUsername();
    }

    // Delete user from database
    @DeleteMapping(value ="/user/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "User " + id + " was deleted";
    }
}