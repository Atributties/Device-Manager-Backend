package com.example.devicemanagerbackend.DTO;

import com.example.devicemanagerbackend.entities.User;
import com.example.devicemanagerbackend.enums.RequestType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    private int id;
    private RequestType requestType;
    private User user;
    private String requestText;
    private List<MessageDTO> messages;
    private LocalDateTime dateCreated;
    private LocalDateTime lastUpdated;

    // Constructors, getters, and setters
}