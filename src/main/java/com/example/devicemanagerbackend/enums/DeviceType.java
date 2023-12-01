package com.example.devicemanagerbackend.enums;

import com.example.devicemanagerbackend.DTO.DeviceTypeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum DeviceType {
    ACTION_CAMERA("AC", "Action Camera"),
    DOCKING_STATION("DS", "Docking Station"),
    HEADSET("HS", "Headset"),
    INTERNET_SUBSCRIPTION("IS", "Internet Subscription"),
    MOBILE_PHONE("MP", "Mobile Phone"),
    PC("PC", "Personal Computer"),
    ROUTER("RT", "Router"),
    SCREEN("SC", "Screen"),
    SMARTPHONE("SP", "Smartphone"),
    TABLET("TB", "Tablet"),
    MOBILE_PHONE_ACCESSORIES("MPA", "Mobile Phone Accessories"),
    TABLET_ACCESSORIES("TA", "Tablet Accessories"),
    GPS("GPS", "GPS"),
    WEBCAM("WC", "Webcam");

    private String abbreviation;
    private String displayName;

    public DeviceTypeDto toDto() {
        return new DeviceTypeDto(abbreviation, displayName, name());
    }
}
