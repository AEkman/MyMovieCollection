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

    @GetMapping(value = "/movie")
    public List<Movie> getMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping(value = "/movie/add")
    public String addMovie(@RequestBody Movie movie) {
        movieService.save(movie);

        return "Movie added";
    }
}
