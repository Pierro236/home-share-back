package com.homeshare.homeshareapi.service;

import com.homeshare.homeshareapi.model.Message;
import com.homeshare.homeshareapi.model.User;
import com.homeshare.homeshareapi.repository.MessageRepository;
import com.homeshare.homeshareapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService{

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Override
    public Message create(UUID userId, Message message) {
        User sender = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        User receiver = userRepository.findById(message.getReceiver().getUserid())
                .orElseThrow(() -> new RuntimeException("Receiver user not found"));

        message.setSender(sender);
        message.setReceiver(receiver);
        message.setCreatedAt(LocalDateTime.now());

        return messageRepository.save(message);
    }

    @Override
    public List<Message> getAllMessages(UUID userId) {
        List<Message> allMessages = messageRepository.findAll();
        List<Message> userMessages = new ArrayList<>();
        for (Message message : allMessages) {
            if (message.getSender().getUserid().equals(userId) || message.getReceiver().getUserid().equals(userId)) {
                userMessages.add(message);
            }
        }
        return userMessages;
    }

    @Override
    public List<Message> getMessagesByUser(UUID userId, UUID recipientId) {
        List<Message> allMessages = messageRepository.findAll();
        List<Message> userMessages = new ArrayList<>();
        for (Message message : allMessages) {
            if ((message.getSender().getUserid().equals(userId) && message.getReceiver().getUserid().equals(recipientId))
                    || (message.getSender().getUserid().equals(recipientId) && message.getReceiver().getUserid().equals(userId))) {
                userMessages.add(message);
            }
        }
        return userMessages;
    }
}
