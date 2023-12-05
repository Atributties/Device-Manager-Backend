package com.example.devicemanagerbackend.DTO;

import com.example.devicemanagerbackend.entities.Device;
import com.example.devicemanagerbackend.entities.User;
import com.example.devicemanagerbackend.enums.DeviceStatus;
import com.example.devicemanagerbackend.enums.DeviceType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class DeviceDTO {
    private String id;
    private String imeiNumber;
    private String serialNumber;
    private DeviceType deviceType;
    private String deviceModel;
    private DeviceStatus deviceStatus;
    private String comments;
    private LocalDateTime dateCreated;
    private LocalDateTime lastUpdated;
    private User user;

    public DeviceDTO(Device device) {
        this.id = device.getId();
        this.imeiNumber = device.getImeiNumber();
        this.serialNumber = device.getSerialNumber();
        this.deviceType = device.getDeviceType();
        this.deviceModel = device.getDeviceModel();
        this.deviceStatus = device.getDeviceStatus();
        this.comments = device.getComments();
        this.dateCreated = device.getDateCreated();
        this.lastUpdated = device.getLastUpdated();
        this.user = device.getUser();
    }


}
