package com.homeshare.homeshareapi.controller;

import com.homeshare.homeshareapi.model.User;
import com.homeshare.homeshareapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public User create(@RequestBody User user){
        return userService.create(user);
    }

    @GetMapping("/read")
    public List<User> read(){
        return userService.read();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id){
        return userService.getUserbyId(id);
    }

    @PutMapping("/update/{id}")
    public User update(@PathVariable UUID id,@RequestBody User user) {
        return userService.modify(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable UUID id) {
        return userService.delete(id);
    }
}
