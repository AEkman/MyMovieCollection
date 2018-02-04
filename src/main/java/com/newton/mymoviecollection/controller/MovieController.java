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


/* Movie - GetMappings */


    // Get all movies from database
    @GetMapping(value = "/movie")
    public List<Movie> getMovies() {
        return movieService.getAllMovies();
    }

    // Get movie by id from database
    @GetMapping(value = "/movie/{imdbId}")
    public Movie getMovieById(@PathVariable String imdbId) {
        return movieService.getMovieByImdbId(imdbId);
    }

    // Get movie by title from database
    @GetMapping(value = "/movie/title/{title}")
    public List<Movie> getMovieByTitle(@PathVariable String title) {
        return movieService.getMovieByTitle(title);
    }

    // Get movie by year from database
    @GetMapping(value = "/movie/year/{year}")
    public List<Movie> getMovieByYear(@PathVariable String year) {
        return movieService.getMovieByYear(year);
    }


/* Movie - PostMappings */


    // Save new movie to database
    @PostMapping(value = "/movie")
    public String addMovie(@RequestBody Movie movie) {
        movieService.saveMovie(movie);

        return "Movie created with id = " + movie.getImdbId() + " and title = " + movie.getTitle();
    }


/* Movie - PutMappings */


    // Update movie in database
    @PutMapping(value = "/movie")
    public String updateMovie(@RequestBody Movie movie){
        movieService.updateMovie(movie);
        return "Movie update" + movie;
    }


/* Movie - DeleteMappings */


    // Delete movie by id from database
    @DeleteMapping(value = "/movie/delete/{imdbId}")
    public String deleteMovie(@PathVariable String imdbId) {
        movieService.deleteMovie(imdbId);

        return "Movie " + imdbId + " deleted";
    }
}