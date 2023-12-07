package com.example.devicemanagerbackend.DTO;

import com.example.devicemanagerbackend.entities.User;
import com.example.devicemanagerbackend.entities.UserRequest;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {

    private int id;
    @JsonBackReference // Breaks the circular reference
    private UserRequest userRequest;
    private User user;
    private String messageText;
    private boolean readed;
    private LocalDateTime dateCreated;


    // Constructors, getters, and setters
}
