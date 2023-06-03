package com.homeshare.homeshareapi.repository;

import com.homeshare.homeshareapi.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
}
