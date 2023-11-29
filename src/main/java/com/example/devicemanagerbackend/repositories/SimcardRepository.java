package com.example.devicemanagerbackend.repositories;

import com.example.devicemanagerbackend.entities.Simcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimcardRepository extends JpaRepository<Simcard, Integer> {
}
