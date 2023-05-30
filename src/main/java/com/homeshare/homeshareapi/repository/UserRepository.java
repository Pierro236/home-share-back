package com.homeshare.homeshareapi.repository;

import com.homeshare.homeshareapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}
