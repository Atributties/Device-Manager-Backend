package com.example.devicemanagerbackend.services;

import com.example.devicemanagerbackend.entities.Device;
import com.example.devicemanagerbackend.exceptions.CustomException;
import com.example.devicemanagerbackend.repositories.DeviceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Transactional
    public Optional<Device> saveDevice(Device device) {
        deviceRepository.save(device);
        return deviceRepository.findById(device.getImeiNumber());
    }



    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    public Optional<Device> getById(String id) {
        return deviceRepository.findById(id);
    }

    @Transactional
    public Device updateDevice(Device updatedDevice) {
        return deviceRepository.findById(updatedDevice.getImeiNumber())
                .map(existingDevice -> {
                    existingDevice.setSerialNumber(updatedDevice.getSerialNumber());
                    existingDevice.setDeviceType(updatedDevice.getDeviceType());
                    existingDevice.setDeviceModel(updatedDevice.getDeviceModel());
                    existingDevice.setDeviceStatus(updatedDevice.getDeviceStatus());
                    existingDevice.setComments(updatedDevice.getComments());

                    return deviceRepository.save(existingDevice);
                })
                .orElseThrow(() -> new CustomException("Device not found"));
    }


    public void delete(Device device) {
        deviceRepository.delete(device);
    }

}
