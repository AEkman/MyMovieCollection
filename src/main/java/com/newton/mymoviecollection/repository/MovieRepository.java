package com.newton.mymoviecollection.repository;

import com.newton.mymoviecollection.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String>{
    Movie findByImdbId(String imdbId);

    List<Movie> findByTitleContaining(String title);

    List<Movie> findByYearContaining(String year);
}