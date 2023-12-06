package com.example.devicemanagerbackend.services;

import com.example.devicemanagerbackend.DTO.LoginResponseDTO;
import com.example.devicemanagerbackend.entities.Role;
import com.example.devicemanagerbackend.entities.User;
import com.example.devicemanagerbackend.enums.UserRole;
import com.example.devicemanagerbackend.repositories.RoleRepository;
import com.example.devicemanagerbackend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public AuthenticationService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            TokenService tokenService
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public User registerUser(String firstname, String middlename, String lastname, String email, String password, UserRole userRole) {
        String encodedPassword = passwordEncoder.encode(password);

        Role role = roleRepository.findByAuthority(userRole.name())
                .orElseThrow(() -> new IllegalArgumentException("Role not found for authority: " + userRole.name()));

        User newUser = new User();
        newUser.setFirstname(firstname);
        newUser.setMiddlename(middlename);
        newUser.setLastname(lastname);
        newUser.setEmail(email);
        newUser.setPassword(encodedPassword);
        newUser.setRole(role);  // Set the single role instead of authorities

        System.out.println("User:" + newUser.toString());

        return userRepository.save(newUser);
    }

    public LoginResponseDTO loginUser(String email, String password) {
        System.out.println("Email:" + email);
        System.out.println("password:" + password);
        // Perform authentication
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        // Set the authentication object in the SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token
        String jwt = tokenService.generateJwt(authentication);

        // Get the authenticated user details
        User authenticatedUser = (User) authentication.getPrincipal();

        // Create and return the LoginResponseDTO
        return new LoginResponseDTO(authenticatedUser, jwt);
    }
}
