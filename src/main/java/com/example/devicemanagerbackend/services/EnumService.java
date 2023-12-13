package com.example.devicemanagerbackend.services;


import com.example.devicemanagerbackend.DTO.RequestTypeDTO;
import com.example.devicemanagerbackend.DTO.StatusDTO;
import com.example.devicemanagerbackend.DTO.DeviceTypeDto;
import com.example.devicemanagerbackend.DTO.UserRolesDTO;
import com.example.devicemanagerbackend.enums.RequestType;
import com.example.devicemanagerbackend.enums.Status;
import com.example.devicemanagerbackend.enums.DeviceType;
import com.example.devicemanagerbackend.enums.UserRole;
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

    public List<StatusDTO> getAllStatusDisplayNames() {
        return Arrays.stream(Status.values())
                .map(Status::toDto)
                .collect(Collectors.toList());
    }

    public List<UserRolesDTO> getAllUserTypesDisplayNames() {
        return Arrays.stream(UserRole.values())
                .map(UserRole::toDto)
                .collect(Collectors.toList());
    }

    public List<RequestTypeDTO> getAllRequestTypeNames() {
        return Arrays.stream(RequestType.values())
                .map(RequestType::toDto)
                .collect(Collectors.toList());
    }
}
