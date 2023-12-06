package com.example.devicemanagerbackend.controllers;

import com.example.devicemanagerbackend.DTO.MessageDTO;
import com.example.devicemanagerbackend.DTO.UserRequestDTO;
import com.example.devicemanagerbackend.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin("*")
public class ChatRestController {

    private final ChatService chatService; // Erstat ChatService med den faktiske serviceklasse

    @Autowired
    public ChatRestController(ChatService chatService) {
        this.chatService = chatService;
    }

    // Opret en ny brugeranmodning
    @PostMapping("/user-requests")
    public ResponseEntity<UserRequestDTO> createUserRequest(@RequestBody UserRequestDTO userRequestDTO) {
        UserRequestDTO createdRequest = chatService.createUserRequest(userRequestDTO);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }

    // Opret en ny besked på en brugeranmodning
    @PostMapping("/user-requests/{requestId}/messages")
    public ResponseEntity<UserRequestDTO> addMessageToUserRequest(@PathVariable int requestId, @RequestBody MessageDTO messageDTO) {
        UserRequestDTO updatedUserRequest = chatService.addMessageToUserRequest(requestId, messageDTO);
        return new ResponseEntity<>(updatedUserRequest, HttpStatus.OK);
    }

    // Hent alle brugeranmodninger (for administrator)
    @GetMapping("/admin/user-requests")
    public ResponseEntity<List<UserRequestDTO>> getAllUserRequestsForAdmin() {
        List<UserRequestDTO> allUserRequests = chatService.getAllUserRequestsWithMessages();
        return new ResponseEntity<>(allUserRequests, HttpStatus.OK);
    }

    // Hent en specifik brugeranmodning med beskeder (for administrator)
    @GetMapping("/admin/user-requests/{requestId}")
    public ResponseEntity<UserRequestDTO> getUserRequestWithMessagesForAdmin(@PathVariable int requestId) {
        UserRequestDTO userRequestWithMessages = chatService.getUserRequestWithMessages(requestId);
        return new ResponseEntity<>(userRequestWithMessages, HttpStatus.OK);
    }

    // Hent alle brugeranmodninger for en specifik bruger
    @GetMapping("/user/{userId}/user-requests")
    public ResponseEntity<List<UserRequestDTO>> getAllUserRequestsForUser(@PathVariable int userId) {
        List<UserRequestDTO> userRequests = chatService.getAllUserRequestsForUser(userId);
        return new ResponseEntity<>(userRequests, HttpStatus.OK);
    }

    // Hent en specifik brugeranmodning med beskeder for en specifik bruger
    @GetMapping("/user/{userId}/user-requests/{requestId}")
    public ResponseEntity<UserRequestDTO> getUserRequestWithMessagesForUser(@PathVariable int userId, @PathVariable int requestId) {
        UserRequestDTO userRequestWithMessages = chatService.getUserRequestWithMessagesForUser(userId, requestId);
        return new ResponseEntity<>(userRequestWithMessages, HttpStatus.OK);
    }
}
