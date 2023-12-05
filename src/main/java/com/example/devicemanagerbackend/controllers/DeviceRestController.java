package com.example.devicemanagerbackend.controllers;

import com.example.devicemanagerbackend.DTO.DeviceDTO;
import com.example.devicemanagerbackend.entities.Device;
import com.example.devicemanagerbackend.entities.User;
import com.example.devicemanagerbackend.enums.DeviceStatus;
import com.example.devicemanagerbackend.enums.DeviceType;
import com.example.devicemanagerbackend.exceptions.CustomException;
import com.example.devicemanagerbackend.services.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/device")
@CrossOrigin("*")
public class DeviceRestController {

    private final DeviceService deviceService;


    public DeviceRestController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices() {
        List<Device> devices = deviceService.findAll();
        return ResponseEntity.ok(devices);
    }

    // Hent en specifik enhed efter ID
    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable String id) {
        return deviceService.getById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CustomException("Device not found with id: " + id));
    }

    @PostMapping
    public ResponseEntity<Device> createDevice(@RequestBody Device device) {
        return deviceService.saveDevice(device)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CustomException("Problem with post device"));
    }

    // Opdater en eksisterende enhed
    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable String id, @RequestBody DeviceDTO updatedDeviceDTO) {

        Device updatedDTO = deviceService.updateDevice(id, updatedDeviceDTO);
        System.out.println(updatedDTO.toString());
        return ResponseEntity.ok(updatedDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable String id) {
        return deviceService.getById(id)
                .map(device -> {
                    deviceService.delete(device);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseThrow(() -> new CustomException("Device not found with ID: " + id));
    }


}
