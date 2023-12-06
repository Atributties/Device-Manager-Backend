package com.example.devicemanagerbackend.repositories;

import com.example.devicemanagerbackend.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
