package com.example.history.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.history.model.Message;
import com.example.history.repository.MessageRepository;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/messages/{receiverId}")
    public List<Message> getMessages(@PathVariable String receiverId) {
        return messageRepository.findByReceiverId(receiverId);
    }

        @GetMapping("/messages/conversation")
    public List<Message> getConversation(@RequestParam String senderId, @RequestParam String receiverId) {
        return messageRepository.findBySenderIdAndReceiverId(senderId, receiverId);
    }


    // user1 and user2 are the two users between whom the conversation is to be fetched
    @GetMapping("/messages/conversation/bidirectional")
public List<Message> getBidirectionalConversation(@RequestParam String user1, @RequestParam String user2) {
    return messageRepository.findConversationBetweenUsers(user1, user2);
}

}
