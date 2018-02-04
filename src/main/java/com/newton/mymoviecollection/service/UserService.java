package com.newton.mymoviecollection.service;

import com.newton.mymoviecollection.entity.Movie;
import com.newton.mymoviecollection.entity.User;
import com.newton.mymoviecollection.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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

    // Update a user by adding movie on database
    public void updateUserAddMovie(User user, Movie movie){

        // If movie doesn't exists add to database
        movieService.saveMovie(movie);

        List<Movie> userMovies = userRepository.findOne(user.getId()).getMovies();

        boolean userMovieAlreadyExists = false;

        // Check if user already has movie
        for(int i = 0; i < userMovies.size(); i++) {

            if(userMovies.get(i).getImdbId().contains(movie.getImdbId())) {
                userMovieAlreadyExists = true;
                break;
            } else {
                userMovieAlreadyExists = false;
            }
        }

        if (userMovieAlreadyExists == false) {
            userMovies.add(movie);
            user.setMovies(userMovies);
            userRepository.save(user);
        }
    }

    // Update a user by deleting movie on database
    public void updateUserDeleteMovie(User user, String imdbId){
        List<Movie> movies = new ArrayList<>();
        userRepository.findOne(user.getId()).getMovies().forEach(movies::add);

        Movie movieToDelete = movieService.getMovieByImdbId(imdbId);

        if (movies.contains(movieToDelete)) {
            movies.remove(movieToDelete);
            user.setMovies(movies);
        } else {
            System.out.println(imdbId + "doesn't exists");
        }

        userRepository.save(user);
    }

    // Get all users from database
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users;
    }

    // Get user by id from database
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

    // Delete user by id from database
    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    // Remove cache
    @CacheEvict(value = "users", allEntries = true)
    public void evictCache() {
    }

    // Get user byt username from database
    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);

        return user;
    }
}
