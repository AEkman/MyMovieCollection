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

    // Get all movies from database
    public List<Movie> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();

        return movies;
    }

    // Get movie by imdbId from database
    public Movie getMovieByImdbId(String imdbId) {
        Movie movie = movieRepository.findByImdbId(imdbId);

        return movie;
    }

    // Get movie by title from database
    public List<Movie> getMovieByTitle(String title) {
        List<Movie> movies = movieRepository.findByTitleContaining(title);

        return movies;
    }

    // Get movie by year from database
    public List<Movie> getMovieByYear(String year) {
        List<Movie> movies = movieRepository.findByYearContaining(year);

        return movies;
    }

    // Save a movie to database
    public void saveMovie(Movie movie) {

        // Check if movie exists in database
        List<Movie> databaseMovies = movieRepository.findAll();
        boolean movieAlreadyExists = false;

        for(int i = 0; i < databaseMovies.size(); i++) {
            if(databaseMovies.get(i).getImdbId().contains(movie.getImdbId())) {
                movieAlreadyExists = true;
                break;
            } else {
                movieAlreadyExists = false;
            }
        }

        if(movieAlreadyExists == false) {
            movieRepository.save(movie);

        }
    }

    // Update a movie on database
    public void updateMovie(Movie movie) {
        movieRepository.save(movie);
    }

    // Delete movie by imdbId from database
    public void deleteMovie(String imdbId) {
        movieRepository.delete(imdbId);
    }
}