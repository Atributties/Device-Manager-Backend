package com.example.devicemanagerbackend.services;

import com.example.devicemanagerbackend.DTO.UserDTO;
import com.example.devicemanagerbackend.entities.User;
import com.example.devicemanagerbackend.enums.UserRole;
import com.example.devicemanagerbackend.enums.UserType;
import com.example.devicemanagerbackend.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testSaveUser() {
        // Arrange
        User user = new User();
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setUserRole(UserRole.USER);

        when(userRepository.save(user)).thenReturn(user);

        // Act
        Optional<User> result = userService.saveUser(user);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(userRepository).save(user);
    }

    @Test
    public void testGenerateFullname() {
        // Arrange
        User user = new User();
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setMiddlename("Smith");

        // Act
        String fullname = userService.generateFullname(user);

        // Assert
        assertEquals("John Smith Doe", fullname);
    }


   /*
   @Test
   public void testFindAllUsers() {
        // Arrange
        List<UserDTO> expectedUser = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(expectedUser);

        // Act
        List<UserDTO> result = userService.findAll();

        // Assert
        assertEquals(expectedUser, result);
    }
*/
    @Test
    public void testGetUsersById() {
        // Arrange
        int employeeId = 6;
        User expectedEmployee = new User();
        when(userRepository.findById(employeeId)).thenReturn(Optional.of(expectedEmployee));

        // Act
        Optional<User> result = userService.getById(employeeId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedEmployee, result.get());
    }

    @Test
    public void testGetUsersByIdNotFound() {
        // Arrange
        int userId = 7;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act
        Optional<User> result = userService.getById(userId);

        // Assert
        assertTrue(result.isEmpty());
    }

}
