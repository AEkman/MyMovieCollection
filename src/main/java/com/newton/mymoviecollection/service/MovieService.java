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
    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }

    // Update a movie on database
    public void updateMovie(Movie movie) {
        movieRepository.save(movie);
    }

    // Get all movies from database
    public List<Movie> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();

        return movies;
    }

    // Get movie by id from database
    public Movie getMovieByImdbId(String imdbId) {
        Movie movie = movieRepository.findByImdbId(imdbId);

        return movie;
    }
}