package com.example.devicemanagerbackend.repositories;

import com.example.devicemanagerbackend.entities.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRequestRepository extends JpaRepository<UserRequest, Integer > {
    List<UserRequest> findByUserId(int userId);

    Optional<UserRequest> findByIdAndUserId(int requestId, int userId);

    List<UserRequest> findAllByUserId(int userId);
}
