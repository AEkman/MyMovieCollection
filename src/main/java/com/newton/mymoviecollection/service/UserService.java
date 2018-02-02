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

    // Update a user by adding movie on database
    public void updateUserAddMovie(User user, Movie movie){
        List<Movie> databaseMovies = movieService.getAllMovies();

        //TODO: fix
        // Check i database has movie, if not add to database
        if(!databaseMovies.equals(movie)) {
            movieService.saveMovie(movie);
        } else {
            System.out.println("movie exits already");
        }

        List<Movie> userMovies = userRepository.findOne(user.getId()).getMovies();

        //TODO: fix
        // Check if user already has movie
        if (userMovies.contains(movie.getImdbId())) {
            System.out.println("movie dupe");
        } else {
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

    public void deleteUser(Long id) {
        userRepository.delete(id);
    }
}
