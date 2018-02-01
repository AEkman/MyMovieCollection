package com.newton.mymoviecollection.repository;

import com.newton.mymoviecollection.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String>{
    Movie findByImdbId(String imdbId);
//
//    @Transactional
//    Long deleteByImdbId(String imdbId);
}