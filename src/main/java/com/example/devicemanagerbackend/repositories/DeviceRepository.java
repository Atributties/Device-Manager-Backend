package com.example.devicemanagerbackend.repositories;

import com.example.devicemanagerbackend.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, String> {

    @Query("SELECT MAX(d.id) FROM Device d WHERE d.id LIKE :deviceTypeAbbreviation%")
    String findMaxDeviceIdForType(@Param("deviceTypeAbbreviation") String deviceTypeAbbreviation);
}
