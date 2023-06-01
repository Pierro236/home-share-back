package com.homeshare.homeshareapi.repository;

import com.homeshare.homeshareapi.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RatingRepository extends JpaRepository<Rating, UUID> {

}
