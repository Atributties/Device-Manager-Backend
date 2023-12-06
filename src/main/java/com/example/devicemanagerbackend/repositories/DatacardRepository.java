package com.example.devicemanagerbackend.repositories;

import com.example.devicemanagerbackend.entities.Datacard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatacardRepository extends JpaRepository<Datacard, Integer> {

    List<Datacard> findByUserId(int userId);
}
