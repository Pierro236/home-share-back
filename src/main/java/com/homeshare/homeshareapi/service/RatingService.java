package com.homeshare.homeshareapi.service;

import com.homeshare.homeshareapi.model.Rating;

import java.util.List;
import java.util.UUID;

public interface RatingService {
    Rating create(Rating rating);

    List<Rating> read();

    Rating modify(UUID id, Rating rating);

    String delete(UUID id);
}
