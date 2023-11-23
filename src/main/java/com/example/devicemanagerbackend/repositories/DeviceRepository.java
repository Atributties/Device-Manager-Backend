package com.example.devicemanagerbackend.repositories;

import com.example.devicemanagerbackend.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, String> {

}
