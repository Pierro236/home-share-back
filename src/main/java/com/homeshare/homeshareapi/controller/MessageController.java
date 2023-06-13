package com.homeshare.homeshareapi.controller;

import com.homeshare.homeshareapi.model.Message;
import com.homeshare.homeshareapi.service.MessageService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
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
        UUID userId =  message.getSender().getUserid();
        return messageService.create(userId, message);
    }

    @GetMapping("/all/{id}")
    public List<Message> getAllMessages(@PathVariable UUID id) {
        return messageService.getAllMessages(id);
    }

    @GetMapping("/{recipientId}")
    public List<Message> getMessagesByUser( @PathVariable UUID recipientId) {
        UUID userId = recipientId;
        return messageService.getMessagesByUser(userId, recipientId);
    }
}
