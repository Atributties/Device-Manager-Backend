package com.example.devicemanagerbackend.services;

import com.example.devicemanagerbackend.entities.Device;
import com.example.devicemanagerbackend.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    // Method for save device with the device id logik we made from deviceIdService
    public Optional<Device> saveDevice(Device device) {
        deviceRepository.save(device);
        return Optional.of(device);
    }


    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    public Optional<Device> getById(String id) {
        return deviceRepository.findById(id);
    }

    public Device updateDevice(Device updatedDevice) {
        return deviceRepository.save(updatedDevice);
    }

    public void delete(Device device) {
        deviceRepository.delete(device);
    }

}
