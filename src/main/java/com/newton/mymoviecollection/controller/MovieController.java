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

    // Get all movies from database
    @GetMapping(value = "/movie")
    public List<Movie> getMovies() {
        return movieService.getAllMovies();
    }

    // Save new movie to database
    @PostMapping(value = "/movie")
    public void addMovie(@RequestBody Movie movie) {
        movieService.save(movie);
    }
}
