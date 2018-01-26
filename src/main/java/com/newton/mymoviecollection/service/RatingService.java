package com.newton.mymoviecollection.service;

import com.newton.mymoviecollection.entity.Rating;
import com.newton.mymoviecollection.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public void save(Rating rating) {
        ratingRepository.save(rating);
    }

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }
}
