package com.newton.mymoviecollection.service;

import com.newton.mymoviecollection.entity.Movie;
import com.newton.mymoviecollection.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    // Save a movie to database
    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    // Get all movies from database
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}
