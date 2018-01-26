package com.newton.mymoviecollection.controller;

import com.newton.mymoviecollection.entity.Rating;
import com.newton.mymoviecollection.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping(value = "/ratings")
    public List<Rating> ratings() {
        return ratingService.getAllRatings();
    }
}
