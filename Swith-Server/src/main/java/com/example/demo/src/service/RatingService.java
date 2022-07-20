package com.example.demo.src.service;

import com.example.demo.src.entity.Rating;
import com.example.demo.src.repository.RatingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class RatingService {

    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }
}
