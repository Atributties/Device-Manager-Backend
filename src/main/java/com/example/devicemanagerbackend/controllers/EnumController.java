package com.example.devicemanagerbackend.controllers;


import com.example.devicemanagerbackend.DTO.DeviceStatusDto;
import com.example.devicemanagerbackend.DTO.DeviceTypeDto;
import com.example.devicemanagerbackend.DTO.UserTypeDto;
import com.example.devicemanagerbackend.services.EnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/enums")
@CrossOrigin("*")
public class EnumController {


    @Autowired
    private EnumService enumService;


    @GetMapping("/devicetypes")
    public ResponseEntity<List<DeviceTypeDto>> getAllDeviceTypeDisplayNames() {
        List<DeviceTypeDto> deviceTypeDisplayNames = enumService.getAllDeviceTypes();
        return new ResponseEntity<>(deviceTypeDisplayNames, HttpStatus.OK);
    }

    @GetMapping("/devicestatus")
    public ResponseEntity<List<DeviceStatusDto>> getAllDeviceStatusDisplayNames() {
        List<DeviceStatusDto> deviceStatusDisplayNames = enumService.getAllDeviceStatusDisplayNames();
        return new ResponseEntity<>(deviceStatusDisplayNames, HttpStatus.OK);
    }
    @GetMapping("/usertypes")
    public ResponseEntity<List<UserTypeDto>> getAllUserTypesDisplayNames() {
        List<UserTypeDto> userTypesDisplayNames = enumService.getAllUserTypesDisplayNames();
        return new ResponseEntity<>(userTypesDisplayNames, HttpStatus.OK);
    }




}
