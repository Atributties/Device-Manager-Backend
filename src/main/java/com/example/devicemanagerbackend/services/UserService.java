package com.example.devicemanagerbackend.services;

import com.example.devicemanagerbackend.entities.User;
import com.example.devicemanagerbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Save employee with the id logik we made in employeeIdService.
    public Optional<User> saveUser(User user) {
        user.setFullName(generateFullname(user)); //Set fullname from the firstname, middlename(if present) and lastname from user input.
        userRepository.save(user);
        return Optional.of(user);
    }

    // Collect fullname
    public String generateFullname(User user) {
        String firstname = user.getFirstname();
        String middleName = user.getMiddleName();
        String lastname = user.getLastname();

        if (middleName != null && !middleName.isEmpty()) {
            return firstname + " " + middleName + " " + lastname;
        } else {
            return firstname + " " + lastname;
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(String id) {
        return getById(id);

    }

    public User updateUser(User updatedUser) {
        return userRepository.save(updatedUser);
    }

    public void deleteUser(User employee) {
        userRepository.delete(employee);
    }

}
