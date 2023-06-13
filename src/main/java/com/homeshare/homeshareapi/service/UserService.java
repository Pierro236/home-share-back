package com.homeshare.homeshareapi.service;

import com.homeshare.homeshareapi.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User create(User user);

    List<User> read();

    User modify(UUID id, User user);

    String delete(UUID id);

    User getUserbyId(UUID id);

    User getUserbyUsername(String username);

    User getUserbyUsernameAndPassword(String username, String password);
}
