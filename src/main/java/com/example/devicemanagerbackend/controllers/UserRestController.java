package com.example.devicemanagerbackend.controllers;

import com.example.devicemanagerbackend.DTO.UserDTO;
import com.example.devicemanagerbackend.entities.Role;
import com.example.devicemanagerbackend.entities.User;
import com.example.devicemanagerbackend.exceptions.CustomException;
import com.example.devicemanagerbackend.repositories.RoleRepository;
import com.example.devicemanagerbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserRestController {

    @Autowired
    private final UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    // Hent en specifik medarbejder efter ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return userService.getById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CustomException("User not found with" + id));// skal nok add custom exeption her
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        System.out.println(user.toString());
        return userService.saveUser(user)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CustomException("Problem with posting user"));// skal nok add custom exeption her
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        return userService.getById(id)
                .map(userToUpdate -> {
                    userToUpdate.setFirstname(user.getFirstname());
                    userToUpdate.setMiddlename(user.getMiddlename());
                    userToUpdate.setLastname(user.getLastname());
                    userToUpdate.setEmail(user.getEmail());
                    userToUpdate.setPassword(user.getPassword());
                    userToUpdate.setUserRole(user.getUserRole());

                    Role updatedRole = roleRepository.findByAuthority(user.getRole().getAuthority())
                            .orElseThrow(() -> new CustomException("Role not found: " + user.getRole().getAuthority()));
                    userToUpdate.setRole(updatedRole);

                    return ResponseEntity.ok(userService.updateUser(userToUpdate));
                })
                .orElseThrow(() -> new CustomException("User not found with ID: " + id));
    }





    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        return userService.getById(id)
                .map(user -> {
                    userService.deleteUser(user);
                    return ResponseEntity.ok(user);
                })
                .orElseThrow(() -> new CustomException("User not found with" + id));// skal nok add custom exeption her
    }

}
