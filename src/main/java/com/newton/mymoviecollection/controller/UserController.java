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

    // Save new user to database
    @PostMapping(value = "/user")
    public String registerUser(@RequestBody User user){
        userService.saveUser(user);
        return "User created with id = " + user.getId() + " and username = " + user.getUsername();
    }

    // Update user in database
    @PutMapping(value = "/user")
    public String updateUser(@RequestBody User user){
        userService.updateUser(user);
        return "User updated";
    }

    // Add movie to user in database
    @PutMapping(value = "/user/{id}/add/movie")
    public String updateUserAddMovie(@PathVariable User id, @RequestBody Movie movie){
        List<Movie> userMovieList = userService.getMoviesByUserId(id.getId());

        //TODO fix dupliate
        if (userMovieList.contains(movie)) {
            return "Movie already exists";
        } else {
            userService.updateUserAddMovie(id, movie);

            return "Movie " + movie.getTitle() + " got added to user " + id.getUsername();
        }
    }

    // Delete movie from user in database
    @DeleteMapping(value = "/user/{id}/add/movie/{imdbId}")
    public String updateUserDeleteMovie(@PathVariable User id, @PathVariable String imdbId){
        userService.updateUserDeleteMovie(id, imdbId);
        return "Movie with imdb id " + imdbId + " deleted from user " + id;
    }

    //TODO: Delete user with movies attached
    // Delete user from database
    @DeleteMapping(value ="/user/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "User " + id + " was deleted";
    }

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

    // Get movies by user
    @GetMapping(value ="/user/{id}/movies")
    public List<Movie> getUserMovies(@PathVariable Long id){
        return userService.getMoviesByUserId(id);
    }
}