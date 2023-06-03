package com.homeshare.homeshareapi.service;

import com.homeshare.homeshareapi.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.homeshare.homeshareapi.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> read() {
        return userRepository.findAll();
    }

    @Override
    public User modify(UUID id, User user) {
        return userRepository.findById(id)
                .map(p -> {
                    p.setLastname(user.getLastname());
                    p.setFirstname(user.getFirstname());
                    p.setEmail(user.getEmail());
                    p.setAddress(user.getAddress());
                    return userRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("User not Existed !"));
    }

    @Override
    public String delete(UUID id) {
        userRepository.deleteById(id);
        return "User Delete !";
    }


    @Override
    public User getUserbyId(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User getUserbyUsername(String username) {
        return getUserbyUsername(username);
    }

}
