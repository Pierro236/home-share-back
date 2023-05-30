package com.homeshare.homeshareapi.controller;

import com.homeshare.homeshareapi.model.Home;
import com.homeshare.homeshareapi.service.HomeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/home")
@AllArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @PostMapping("/create")
    public Home create(@RequestBody Home home){
        return homeService.create(home);
    }

    @GetMapping("/read")

    public List<Home> read(){
        return homeService.read();
    }

    @PutMapping("/update/{id}")
    public Home update(@PathVariable UUID id, @RequestBody Home home) {
        return homeService.modify(id, home);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable UUID id) {
        return homeService.delete(id);
    }
}
