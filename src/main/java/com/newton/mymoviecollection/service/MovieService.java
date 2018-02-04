package com.newton.mymoviecollection.service;

import com.newton.mymoviecollection.entity.Movie;
import com.newton.mymoviecollection.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    // Get all movies from database
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // Get movie by imdbId from database
    public Movie getMovieByImdbId(String imdbId) {
        return movieRepository.findByImdbId(imdbId);
    }

    // Get movie by title from database
    public List<Movie> getMovieByTitle(String title) {
        List<Movie> movies = movieRepository.findByTitleContaining(title);

        movies.sort(Comparator.comparing(Movie::getTitle));

        return movies;
    }

    // Get movie by year from database
    public List<Movie> getMovieByYear(String year) {
        List<Movie> movies = movieRepository.findByYearContaining(year);

        movies.sort(Comparator.comparing(Movie::getYear));

        return movies;
    }

    // Save a movie to database
    public void saveMovie(Movie movie) {

        // Check if movie exists in database
        List<Movie> databaseMovies = movieRepository.findAll();
        boolean movieAlreadyExists = false;

        for (Movie databaseMovie : databaseMovies) {
            if (databaseMovie.getImdbId().contains(movie.getImdbId())) {
                movieAlreadyExists = true;
                break;
            } else {
                movieAlreadyExists = false;
            }
        }

        if(!movieAlreadyExists) {
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