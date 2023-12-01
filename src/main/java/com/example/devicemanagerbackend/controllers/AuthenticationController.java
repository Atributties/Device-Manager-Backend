package com.example.devicemanagerbackend.controllers;


import com.example.devicemanagerbackend.DTO.LoginResponseDTO;
import com.example.devicemanagerbackend.DTO.RegistrationDTO;
import com.example.devicemanagerbackend.entities.User;
import com.example.devicemanagerbackend.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public User registerUser(@RequestBody RegistrationDTO body) {
        return authenticationService.registerUser(body.getFirstName(), body.getMiddleName(), body.getLastName(), body.getEmail(), body.getPassword(), body.getUserType());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body) {
        return authenticationService.loginUser(body.getEmail(), body.getPassword());
    }
}