package com.homeshare.homeshareapi.service;

import com.homeshare.homeshareapi.model.Message;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    Message create(UUID userId, Message message);
    List<Message> getAllMessages(UUID userId);
    List<Message> getMessagesByUser(UUID userId, UUID recipientId);
}
