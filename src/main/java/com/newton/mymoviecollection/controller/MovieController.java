package com.newton.mymoviecollection.controller;

import com.newton.mymoviecollection.entity.Movie;
import com.newton.mymoviecollection.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    // Save new movie to database
    @PostMapping(value = "/movie")
    public String addMovie(@RequestBody Movie movie) {
        movieService.saveMovie(movie);

        return "Movie created with id = " + movie.getId() + " and title = " + movie.getTitle();
    }

    // Update movie in database
    @PutMapping(value = "/movie")
    public String updateMovie(@RequestBody Movie movie){
        movieService.updateMovie(movie);
        return "Movie update" + movie;
    }

    // Get all movies from database
    @GetMapping(value = "/movie")
    public List<Movie> getMovies() {
        return movieService.getAllMovies();
    }

    // Get movie by id from database
    @GetMapping(value = "/movie/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }
}