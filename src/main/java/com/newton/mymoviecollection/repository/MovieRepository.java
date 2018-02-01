package com.newton.mymoviecollection.repository;

import com.newton.mymoviecollection.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
    Movie findByImdbId(String imdbId);
}