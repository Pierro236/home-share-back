package com.homeshare.homeshareapi.controller;

import com.homeshare.homeshareapi.model.User;
import com.homeshare.homeshareapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/read")
    public ResponseEntity<List<User>> read() {
        List<User> users = userService.read();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/create")
    public User create(@RequestBody User user){
        return userService.create(user);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        User user = userService.getUserbyId(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> update(@PathVariable UUID id, @RequestBody User user) {
        User updatedUser = userService.modify(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = userService.delete(id);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestParam String username, @RequestParam String password) {
        User user = userService.getUserbyUsernameAndPassword(username, password);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }

        if (!Objects.equals(user.getPassword(), password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password!");
        }

        return ResponseEntity.ok(user.getUserid());
    }

}
