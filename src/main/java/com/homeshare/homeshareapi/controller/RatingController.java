package com.homeshare.homeshareapi.controller;

import com.homeshare.homeshareapi.model.Home;
import com.homeshare.homeshareapi.model.Rating;
import com.homeshare.homeshareapi.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rating")
@AllArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping("/create")
    public Rating create(@RequestBody Rating rating){
        return ratingService.create(rating);
    }

    @GetMapping("/read")

    public List<Rating> read(){
        return ratingService.read();
    }

    @PutMapping("/update/{id}")
    public Rating update(@PathVariable UUID id, @RequestBody Rating rating) {
        return ratingService.modify(id, rating);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable UUID id) {
        return ratingService.delete(id);
    }

}
