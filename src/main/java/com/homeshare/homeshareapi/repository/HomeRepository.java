package com.homeshare.homeshareapi.repository;

import com.homeshare.homeshareapi.model.Home;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HomeRepository extends JpaRepository<Home, UUID> {

}
