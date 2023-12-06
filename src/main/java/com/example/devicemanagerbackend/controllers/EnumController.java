package com.example.devicemanagerbackend.controllers;


import com.example.devicemanagerbackend.DTO.StatusDTO;
import com.example.devicemanagerbackend.DTO.DeviceTypeDto;
import com.example.devicemanagerbackend.DTO.UserRolesDTO;
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

    @GetMapping("/status")
    public ResponseEntity<List<StatusDTO>> getAllStatusDisplayNames() {
        List<StatusDTO> deviceStatusDisplayNames = enumService.getAllStatusDisplayNames();
        return new ResponseEntity<>(deviceStatusDisplayNames, HttpStatus.OK);
    }
    @GetMapping("/userRoles")
    public ResponseEntity<List<UserRolesDTO>> getAllUserTypesDisplayNames() {
        List<UserRolesDTO> userTypesDisplayNames = enumService.getAllUserTypesDisplayNames();
        return new ResponseEntity<>(userTypesDisplayNames, HttpStatus.OK);
    }




}
