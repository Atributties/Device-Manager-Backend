package com.example.devicemanagerbackend.repositories;

import com.example.devicemanagerbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {


    @Query("SELECT MAX(e.id) FROM User e")
    String findMaxUserId();

    Optional<User> findById(int id);

    Optional<User> findByEmail(String email);
}
