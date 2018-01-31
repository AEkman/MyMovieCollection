package com.newton.mymoviecollection.service;

import com.newton.mymoviecollection.entity.Movie;
import com.newton.mymoviecollection.entity.User;
import com.newton.mymoviecollection.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieService movieService;

    // Save user to database
    public void saveUser(User user){
        userRepository.save(user);
    }

    // Update a user on database
    public void updateUser(User user){
        List<Movie> movies = new ArrayList<>();
        userRepository.findOne(user.getId()).getMovies().forEach(movies::add);
        user.setMovies(movies);

        userRepository.save(user);
    }

    // Get all users from database
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users;
    }

    // Get users by firstName from database
    public User getUserById(Long id) {
        User user = userRepository.findOne(id);

        return user;
    }

    // Get all movies from a users list
    public List<Movie> getMoviesByUserId(Long id) {
        List<Movie> movies = new ArrayList<>();
        userRepository.findOne(id).getMovies().forEach(movies::add);

        return movies;
    }
}
