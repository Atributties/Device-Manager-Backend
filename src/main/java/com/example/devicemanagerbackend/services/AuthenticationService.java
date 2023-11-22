package com.example.devicemanagerbackend.services;

import com.example.devicemanagerbackend.DTO.LoginResponseDTO;
import com.example.devicemanagerbackend.entities.Role;
import com.example.devicemanagerbackend.entities.User;
import com.example.devicemanagerbackend.enums.UserType;
import com.example.devicemanagerbackend.repositories.RoleRepository;
import com.example.devicemanagerbackend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public User registerUser(String firstname, String middlename, String lastname, String email, String password, UserType userType) {
        String encodedPassword = passwordEncoder.encode(password);

        // Fetch the default role for the user type
        String defaultRole = getDefaultRoleForUserType(userType);
        Optional<Role> userRoleOptional = roleRepository.findByAuthority(defaultRole);

        if (!userRoleOptional.isPresent()) {
            throw new RuntimeException("Default role not found: " + defaultRole);
        }

        Set<Role> authorities = new HashSet<>();
        authorities.add(userRoleOptional.get());

        User newUser = new User();
        newUser.setFirstname(firstname);
        newUser.setMiddlename(middlename);
        newUser.setLastname(lastname);
        newUser.setEmail(email);
        newUser.setPassword(encodedPassword);
        newUser.setAuthorities(authorities);
        newUser.setUserType(userType);

        return userRepository.save(newUser);
    }

    private String getDefaultRoleForUserType(UserType userType) {
        switch (userType) {
            case SYSTEM_ADMIN:
                return "SYSTEM_ADMIN";
            case DEVICE_ADMIN:
                return "DEVICE_ADMIN";
            default:
                return "USER";
        }
    }

    public LoginResponseDTO loginUser(String email, String password){

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            String token = tokenService.generateJwt(auth);

            return new LoginResponseDTO(userRepository.findByEmail(email).get(), token);

        } catch(AuthenticationException e){
            return new LoginResponseDTO(null, "");
        }
    }
}
