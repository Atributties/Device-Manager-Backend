package com.example.devicemanagerbackend.enums;

import com.example.devicemanagerbackend.DTO.RequestTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum RequestType {
    ACTION_CAMERA("Action Camera"),
    DOCKING_STATION("Docking Station"),
    HEADSET("Headset"),
    INTERNET_SUBSCRIPTION("Internet Subscription"),
    MOBILE_PHONE("Mobile Phone"),
    PC("Personal Computer"),
    ROUTER("Router"),
    SCREEN("Screen"),
    SMARTPHONE("Smartphone"),
    TABLET("Tablet"),
    MOBILE_PHONE_ACCESSORIES("Mobile Phone Accessories"),
    TABLET_ACCESSORIES("Tablet Accessories"),
    GPS("GPS"),
    WEBCAM("Webcam"),
    SIMCARD("Simcard"),
    DATACARD("Datacard");

    private String displayName;

    public RequestTypeDTO toDto() {
        return new RequestTypeDTO(displayName, name());
    }



}