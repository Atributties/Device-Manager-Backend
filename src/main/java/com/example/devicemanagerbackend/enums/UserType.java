package com.example.devicemanagerbackend.enums;


import com.example.devicemanagerbackend.DTO.DeviceStatusDto;
import com.example.devicemanagerbackend.DTO.UserTypeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum UserType {
    SYSTEM_ADMIN("System Administrator"),
    DEVICE_ADMIN("Device Administrator"),
    USER("Regular User");

    private String displayName;

    public UserTypeDto toDto() {
        return new UserTypeDto(displayName, name());
    }


}
