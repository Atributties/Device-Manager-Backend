package com.example.devicemanagerbackend.services;

import com.example.devicemanagerbackend.DTO.DeviceDTO;
import com.example.devicemanagerbackend.entities.Device;
import com.example.devicemanagerbackend.entities.User;
import com.example.devicemanagerbackend.enums.DeviceStatus;
import com.example.devicemanagerbackend.enums.DeviceType;
import com.example.devicemanagerbackend.exceptions.CustomException;
import com.example.devicemanagerbackend.repositories.DeviceRepository;
import com.example.devicemanagerbackend.repositories.UserRepository;
import com.example.devicemanagerbackend.services.idService.DeviceIdService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceIdService deviceIdService;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Optional<Device> saveDevice(Device device) {
        String nextId = deviceIdService.generateNextDeviceId(device.getDeviceType()); //generate and get the next id
        device.setId(nextId);
        deviceRepository.save(device);
        return Optional.of(device);
    }

    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    public Optional<Device> getById(String id) {
        return deviceRepository.findById(id);
    }

    @Transactional
    public Device updateDevice(String id, DeviceDTO updatedDeviceDTO) {
        return deviceRepository.findById(id)
                .map(existingDevice -> {
                    // Update device details
                    existingDevice.setImeiNumber(updatedDeviceDTO.getImeiNumber());
                    existingDevice.setSerialNumber(updatedDeviceDTO.getSerialNumber());
                    existingDevice.setDeviceType(updatedDeviceDTO.getDeviceType());
                    existingDevice.setDeviceModel(updatedDeviceDTO.getDeviceModel());
                    existingDevice.setDeviceStatus(updatedDeviceDTO.getDeviceStatus());
                    existingDevice.setComments(updatedDeviceDTO.getComments());

                    // Check if user is assigned
                    if (updatedDeviceDTO.getUser() != null) {
                        int userId = updatedDeviceDTO.getUser().getId();
                        if (userId != 0) {
                            // Assign the device to the user
                            User user = userRepository.findById(userId)
                                    .orElseThrow(() -> new CustomException("User not found with id: " + userId));
                            existingDevice.setUser(user);
                            existingDevice.setDeviceStatus(DeviceStatus.IN_USE);
                        }
                    } else {
                        // User is not assigned, set user to null and update device status
                        existingDevice.setUser(null);
                        existingDevice.setDeviceStatus(DeviceStatus.IN_STORAGE);
                    }

                    return deviceRepository.save(existingDevice);
                })
                .orElseThrow(() -> new CustomException("Device not found with ID: " + id));
    }



    public void delete(Device device) {
        deviceRepository.delete(device);
    }



}
