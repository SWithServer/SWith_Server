package com.example.demo.src.service;

import com.example.demo.src.entity.Interest;
import com.example.demo.src.repository.InterestRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class InterestService {

    private final InterestRepository interestRepository;

    public InterestService(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }

    public void make_interest(Interest interest) {
        interestRepository.save(interest);
    }


}
