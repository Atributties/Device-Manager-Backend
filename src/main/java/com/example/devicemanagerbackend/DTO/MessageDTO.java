package com.example.devicemanagerbackend.DTO;

import com.example.devicemanagerbackend.entities.User;
import com.example.devicemanagerbackend.entities.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {

    private int id;
    private UserRequest userRequest;
    private User user;
    private String messageText;
    private LocalDateTime dateCreated;

    // Constructors, getters, and setters
}
