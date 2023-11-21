package com.example.devicemanagerbackend.controllers;

import com.example.devicemanagerbackend.entities.User;
import com.example.devicemanagerbackend.exceptions.CustomException;
import com.example.devicemanagerbackend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    // Hent en specifik medarbejder efter ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return userService.getById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CustomException("User not found with" + id));// skal nok add custom exeption her
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return userService.saveUser(user)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CustomException("Problem with posting user"));// skal nok add custom exeption her
    }

}
