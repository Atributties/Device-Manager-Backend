package com.example.devicemanagerbackend.DTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeviceTypeDto {
    private String abbreviation;
    private String displayName;
    private String enumValue;
}
