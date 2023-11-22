package com.example.devicemanagerbackend.repositories;

import com.example.devicemanagerbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findById(int id);

    Optional<User> findByEmail(String email);
}
