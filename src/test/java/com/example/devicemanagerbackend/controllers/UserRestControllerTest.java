package com.example.devicemanagerbackend.controllers;

import com.example.devicemanagerbackend.entities.User;
import com.example.devicemanagerbackend.exceptions.CustomException;
import com.example.devicemanagerbackend.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRestControllerTest {

    @InjectMocks
    UserRestController userRestController;

    @Mock
    UserService userService;

    @Test
    public void testGetAllUsers() {
        // Arrange
        List<User> expectedUsers = new ArrayList<>();

        // Mock the service to return the expected employees
        when(userService.findAll()).thenReturn(expectedUsers);

        // Act
        ResponseEntity<List<User>> response = userRestController.getAllUsers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUsers, response.getBody());
    }
    @Test
    public void testGetUserById() {
        // Arrange
        String employeeId = "TL0001";
        User expectedEmployee = new User();

        // Mock the service to return the expected employee
        when(userService.getById(employeeId)).thenReturn(Optional.of(expectedEmployee));

        // Act
        ResponseEntity<User> response = userRestController.getUserById(employeeId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedEmployee, response.getBody());
    }

    @Test
    public void testGetUserByIdWithInvalidId() {
        // Arrange
        String invalidUserId = "InvalidID";

        // Mock the service to return an empty Optional to simulate a missing employee
        when(userService.getById(invalidUserId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(CustomException.class, () -> userRestController.getUserById(invalidUserId));
    }



    @Test
    public void testCreateUser() {
        // Arrange
        User newUser = new User();

        // Mock the service to return the saved employee
        when(userService.saveUser(newUser)).thenReturn(Optional.of(newUser));

        // Act
        ResponseEntity<User> response = userRestController.createUser(newUser);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newUser, response.getBody());
    }

}
