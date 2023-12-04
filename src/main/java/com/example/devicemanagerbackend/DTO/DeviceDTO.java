package com.example.devicemanagerbackend.DTO;

import com.example.devicemanagerbackend.entities.Device;
import com.example.devicemanagerbackend.enums.DeviceStatus;
import com.example.devicemanagerbackend.enums.DeviceType;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class DeviceDTO {
    private String id;
    private UserDTO user;
    private String imeiNumber;
    private String serialNumber;
    private DeviceType deviceType;
    private String deviceModel;
    private DeviceStatus deviceStatus;
    private String comments;
    private LocalDateTime dateCreated;
    private LocalDateTime lastUpdated;

    public DeviceDTO(Device device){
        this.id = device.getId();
        if (device.getUser() != null){
            this.user = new UserDTO(device.getUser());

        }
    }
}
