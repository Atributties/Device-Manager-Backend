package com.example.devicemanagerbackend.services;

import com.example.devicemanagerbackend.entities.Device;
import com.example.devicemanagerbackend.exceptions.CustomException;
import com.example.devicemanagerbackend.repositories.DeviceRepository;
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
    public Device updateDevice(String id, Device updatedDevice) {
        return deviceRepository.findById(id)
                .map(existingDevice -> {
                    existingDevice.setImeiNumber(updatedDevice.getImeiNumber());
                    existingDevice.setSerialNumber(updatedDevice.getSerialNumber());
                    existingDevice.setDeviceType(updatedDevice.getDeviceType());
                    existingDevice.setDeviceModel(updatedDevice.getDeviceModel());
                    existingDevice.setDeviceStatus(updatedDevice.getDeviceStatus());
                    existingDevice.setComments(updatedDevice.getComments());

                    return deviceRepository.save(existingDevice);
                })
                .orElseThrow(() -> new CustomException("Device not found with ID: " + id));
    }



    public void delete(Device device) {
        deviceRepository.delete(device);
    }

}
