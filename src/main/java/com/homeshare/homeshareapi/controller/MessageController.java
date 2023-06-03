package com.homeshare.homeshareapi.controller;

import com.homeshare.homeshareapi.model.Message;
import com.homeshare.homeshareapi.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/message")
@AllArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/create")
    public Message createMessage(Authentication authentication, @RequestBody Message message) {
        UUID userId = (UUID) authentication.getPrincipal();
        return messageService.create(userId, message);
    }

    @GetMapping("/all")
    public List<Message> getAllMessages(Authentication authentication) {
        UUID userId = (UUID) authentication.getPrincipal();
        return messageService.getAllMessages(userId);
    }

    @GetMapping("/{recipientId}")
    public List<Message> getMessagesByUser(Authentication authentication, @PathVariable UUID recipientId) {
        UUID userId = (UUID) authentication.getPrincipal();
        return messageService.getMessagesByUser(userId, recipientId);
    }
}
