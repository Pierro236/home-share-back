package com.homeshare.homeshareapi.repository;

import com.homeshare.homeshareapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT u FROM User u WHERE u.email = :username AND u.password = :password")
    User getUserByUsernameAndPassword(String username, String password);
}
