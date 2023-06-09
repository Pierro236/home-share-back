package com.homeshare.homeshareapi.repository;

import com.homeshare.homeshareapi.model.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface HomeRepository extends JpaRepository<Home, UUID> {

}
