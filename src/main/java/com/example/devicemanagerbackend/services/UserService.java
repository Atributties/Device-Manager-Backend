package com.example.devicemanagerbackend.services;

import com.example.devicemanagerbackend.DTO.UserDTO;
import com.example.devicemanagerbackend.entities.User;
import com.example.devicemanagerbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        System.out.println("in the user details service");

        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("email " + email + " not found"));


    }

    // Save employee with the id logik we made in employeeIdService.
    public Optional<User> saveUser(User user) {
        userRepository.save(user);
        return Optional.of(user);
    }

    // Collect fullname
    public String generateFullname(User user) {
        String firstname = user.getFirstname();
        String middleName = user.getMiddlename();
        String lastname = user.getLastname();

        if (middleName != null && !middleName.isEmpty()) {
            return firstname + " " + middleName + " " + lastname;
        } else {
            return firstname + " " + lastname;
        }
    }

    @Transactional
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<User> getById(int id) {
        return userRepository.findById(id);

    }

    public User updateUser(User updatedUser) {
        return userRepository.save(updatedUser);
    }

    public void deleteUser(User employee) {
        userRepository.delete(employee);
    }

}
