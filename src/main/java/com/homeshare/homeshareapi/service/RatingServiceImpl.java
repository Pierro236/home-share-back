package com.homeshare.homeshareapi.service;

import com.homeshare.homeshareapi.model.Rating;
import com.homeshare.homeshareapi.repository.RatingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService{
    private RatingRepository ratingRepository;

    @Override
    public Rating create(Rating rating){
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> read() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating modify(UUID id, Rating rating){
        return ratingRepository.findById(id)
                .map(p -> {
                    p.setHomeId(rating.getHomeId());
                    p.setUserId(rating.getUserId());
                    p.setRatingUserHome(rating.getRatingUserHome());
                    return ratingRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("No Rating !"));
    }

    @Override
    public String delete(UUID id){
        ratingRepository.deleteById(id);
        return "Rating delete !";
    }
}
