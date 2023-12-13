package com.example.devicemanagerbackend.controllers;

import com.example.devicemanagerbackend.DTO.MessageDTO;
import com.example.devicemanagerbackend.DTO.UserRequestDTO;
import com.example.devicemanagerbackend.entities.UserRequest;
import com.example.devicemanagerbackend.exceptions.CustomException;
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
    public ResponseEntity<UserRequest> addMessageToUserRequest(@PathVariable int requestId, @RequestBody MessageDTO messageDTO) {
        return chatService.addMessageToUserRequest(requestId, messageDTO)
                .map(ResponseEntity::ok).orElseThrow(() -> new CustomException("problem with add new message"));
    }


    // Hent alle brugeranmodninger (for administrator)
    @GetMapping("/admin/user-requests")
    public ResponseEntity<List<UserRequestDTO>> getAllUserRequestsForAdmin() {
        return chatService.getAllUserRequestsWithMessages()
                .map(ResponseEntity::ok)
                .orElseThrow(()-> new CustomException("problem with get all UserRequest"));

    }

    // Hent en specifik brugeranmodning med beskeder (for administrator)
    @GetMapping("/admin/user-requests/{requestId}")
    public ResponseEntity<UserRequestDTO> getUserRequestById(@PathVariable int requestId) {
        UserRequestDTO userRequest = chatService.getUserRequest(requestId);
        return new ResponseEntity<>(userRequest, HttpStatus.OK);
    }

    // Hent alle brugeranmodninger for en specifik bruger
    @GetMapping("/user/{userId}/user-requests")
    public ResponseEntity<List<UserRequestDTO>> getAllUserRequestsForUser(@PathVariable int userId) {
        List<UserRequestDTO> userRequests = chatService.getAllUserRequestsForUser(userId);
        return new ResponseEntity<>(userRequests, HttpStatus.OK);
    }

}
