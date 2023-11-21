package com.example.devicemanagerbackend.repositories;

import com.example.devicemanagerbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
