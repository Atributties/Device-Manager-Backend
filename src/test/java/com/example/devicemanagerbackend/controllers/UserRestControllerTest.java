package com.example.devicemanagerbackend.controllers;

import com.example.devicemanagerbackend.entities.User;
import com.example.devicemanagerbackend.exceptions.CustomException;
import com.example.devicemanagerbackend.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void testGetUserById() {
        // Arrange
        int userId = 1;
        User expectedUser = new User();
        expectedUser.setId(userId);

        // Mock the service to return the expected employee
        when(userService.getById(userId)).thenReturn(Optional.of(expectedUser));

        // Act
        ResponseEntity<User> response = userRestController.getUserById(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUser, response.getBody());
    }

    @Test
    public void testGetUserByIdWithInvalidId() {
        // Arrange
        int invalidUserId = 10;

        // Mock the service to return an empty Optional to simulate a missing employee
        when(userService.getById(invalidUserId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(CustomException.class, () -> userRestController.getUserById(invalidUserId));
    }



    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void testCreateUser() {
        // Arrange
        User userToCreate = new User();
        userToCreate.setId(1);

        // Mock the service to return the created employee
        when(userService.saveUser(userToCreate)).thenReturn(Optional.of(userToCreate));

        // Act
        ResponseEntity<User> response = userRestController.createUser(userToCreate);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userToCreate, response.getBody());
    }

    /*
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void testUpdateUser(int userId) {
        // Arrange
        User existingUser = new User();
        existingUser.setId(userId);

        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setFirstname("Adem");

        // Mock the service to return the updated employee
        when(userService.getById(userId)).thenReturn(Optional.of(existingUser));

        when(userService.updateUser(any(User.class))).thenAnswer(invocation -> {
            User userToUpdate = invocation.getArgument(0);
            // Update only the firstname property
            existingUser.setFirstname(userToUpdate.getFirstname());
            return existingUser;
        });

        // Act
        ResponseEntity<User> response = userRestController.updateUser(userId, updatedUser);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUser.getFirstname(), response.getBody().getFirstname());
    }

     */

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void testDeleteUser(String userId) {
        // Arrange
        User userToDelete = new User();
        userToDelete.setId(userId);

        // Mock the service to return the deleted employee
        when(userService.getById(userId)).thenReturn(Optional.of(userToDelete));

        // Act
        ResponseEntity<User> response = userRestController.deleteUser(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userToDelete, response.getBody());
    }

}
