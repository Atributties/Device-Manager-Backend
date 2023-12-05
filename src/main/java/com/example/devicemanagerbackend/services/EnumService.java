package com.example.devicemanagerbackend.services;


import com.example.devicemanagerbackend.DTO.DeviceStatusDto;
import com.example.devicemanagerbackend.DTO.DeviceTypeDto;
import com.example.devicemanagerbackend.DTO.UserRolesDTO;
import com.example.devicemanagerbackend.enums.DeviceStatus;
import com.example.devicemanagerbackend.enums.DeviceType;
import com.example.devicemanagerbackend.enums.UserRole;
import org.hibernate.usertype.UserType;
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

    public List<UserRolesDTO> getAllUserTypesDisplayNames() {
        return Arrays.stream(UserRole.values())
                .map(UserRole::toDto)
                .collect(Collectors.toList());
    }
}
