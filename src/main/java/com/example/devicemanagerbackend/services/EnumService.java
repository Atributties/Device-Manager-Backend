package com.example.devicemanagerbackend.services;


import com.example.devicemanagerbackend.DTO.DeviceStatusDto;
import com.example.devicemanagerbackend.DTO.DeviceTypeDto;
import com.example.devicemanagerbackend.DTO.UserTypeDto;
import com.example.devicemanagerbackend.enums.DeviceStatus;
import com.example.devicemanagerbackend.enums.DeviceType;
import com.example.devicemanagerbackend.enums.UserType;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnumService {

    public List<DeviceTypeDto> getAllDeviceTypes() {
        return Arrays.stream(DeviceType.values())
                .map(DeviceType::toDto)
                .collect(Collectors.toList());
    }

    public List<DeviceStatusDto> getAllDeviceStatusDisplayNames() {
        return Arrays.stream(DeviceStatus.values())
                .map(DeviceStatus::toDto)
                .collect(Collectors.toList());
    }

    public List<UserTypeDto> getAllUserTypesDisplayNames() {
        return Arrays.stream(UserType.values())
                .map(UserType::toDto)
                .collect(Collectors.toList());
    }
}
