package com.example.devicemanagerbackend;

import com.example.devicemanagerbackend.entities.User;
import com.example.devicemanagerbackend.enums.UserType;
import com.example.devicemanagerbackend.repositories.RoleRepository;
import com.example.devicemanagerbackend.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.devicemanagerbackend.entities.Role;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DeviceManagerBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeviceManagerBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
        return args -> {
            if (roleRepository.findByAuthority("SYSTEM_ADMIN").isPresent()) return;
            Role adminRole = roleRepository.save(new Role("SYSTEM_ADMIN"));
            roleRepository.save(new Role("USER"));
            roleRepository.save(new Role("DEVICE_ADMIN"));

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            User admin = new User();
            admin.setFirstname("Lars");
            admin.setMiddlename("Hans");
            admin.setLastname("Christiansen");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("password"));
            admin.setAuthorities(roles);
            admin.setUserType(UserType.SYSTEM_ADMIN);

            userRepository.save(admin);
        };
    }
}
