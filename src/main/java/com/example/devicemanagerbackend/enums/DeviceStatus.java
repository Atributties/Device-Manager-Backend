package com.example.devicemanagerbackend.enums;

import com.example.devicemanagerbackend.DTO.DeviceStatusDto;
import com.example.devicemanagerbackend.DTO.DeviceTypeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum DeviceStatus {
    IN_USE("In Use"),
    IN_STORAGE("In Storage"),
    UNDER_REPAIR("Under Repair"),
    RETIRED("Retired");

    private String displayName;

    public DeviceStatusDto toDto() {
        return new DeviceStatusDto(displayName, name());
    }

}
