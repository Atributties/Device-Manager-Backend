package com.example.devicemanagerbackend.controllers;

import com.example.devicemanagerbackend.entities.Device;
import com.example.devicemanagerbackend.exceptions.CustomException;
import com.example.devicemanagerbackend.services.DeviceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
/*
@ExtendWith(MockitoExtension.class)
class DeviceRestControllerTest {

    @InjectMocks
    private DeviceRestController deviceRestController;

    @Mock
    DeviceService deviceService;

    //Get all devices test
    @Test
    public void testGetAllDevices() {
        // Arrange
        List<Device> expectedDevices = new ArrayList<>();

        // Mock the service to return the expected devices
        when(deviceService.findAll()).thenReturn(expectedDevices);

        // Act
        ResponseEntity<List<Device>> response = deviceRestController.getAllDevices();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDevices, response.getBody());
    }
    @Test
    public void testGetAllDevicesException() {
        // Arrange
        List<Device> expectedDevices = new ArrayList<>();
        when(deviceService.findAll()).thenReturn(expectedDevices);

        // Simulér en fejl i servicekaldet
        when(deviceService.findAll()).thenThrow(new CustomException("Service exception"));

        // Act and Assert
        assertThrows(CustomException.class, () -> deviceRestController.getAllDevices());
    }




    //Test for get device with id
    @Test
    public void testGetDeviceById() {
        // Arrange
        String deviceId = "TB0001";
        Device expectedDevice = new Device();

        // Mock the service to return the expected device
        when(deviceService.getById(deviceId)).thenReturn(Optional.of(expectedDevice));

        // Act
        ResponseEntity<Device> response = deviceRestController.getDeviceById(deviceId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDevice, response.getBody());
    }
    @Test
    public void testGetDeviceByIdWithInvalidId() {
        // Arrange
        String invalidDeviceId = "InvalidID";

        // Mock the service to return an empty Optional to simulate a missing device
        when(deviceService.getById(invalidDeviceId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(CustomException.class, () -> deviceRestController.getDeviceById(invalidDeviceId));
    }



    // Test for create device
    @Test
    public void testCreateDevice() {
        // Arrange
        Device newDevice = new Device();

        // Mock the service to return the saved device
        when(deviceService.saveDevice(newDevice)).thenReturn(Optional.of(newDevice));

        // Act
        ResponseEntity<Device> response = deviceRestController.createDevice(newDevice);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newDevice, response.getBody());
    }

    @Test
    public void testCreateDeviceException() {
        // Arrange
        Device newDevice = new Device();
        when(deviceService.saveDevice(newDevice)).thenReturn(Optional.of(newDevice));

        // Simulér en fejl i servicekaldet
        when(deviceService.saveDevice(newDevice)).thenThrow(new CustomException("Service exception"));

        // Act and Assert
        assertThrows(CustomException.class, () -> deviceRestController.createDevice(newDevice));
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void testUpdateDevice() {
        // Arrange
        String deviceId = "TB0001";
        Device updatedDevice = new Device(
                "AD1233432211233",    // id
                "AD1233432211233",    // imeiNumber
                "Serial123",          // serialNumber
                DeviceType.TABLET,    // deviceType
                "ModelX",             // deviceModel
                DeviceStatus.IN_USE,  // deviceStatus
                "Some comments",      // comments
                LocalDateTime.now(),  // dateCreated
                LocalDateTime.now()   // lastUpdated
        );


        // Mock the service to return the updated device when updated
        when(deviceService.updateDevice(eq(deviceId), any(Device.class))).thenReturn(updatedDevice);

        // Act
        ResponseEntity<Device> response = deviceRestController.updateDevice(deviceId, updatedDevice);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedDevice, response.getBody());
    }





    @Test
    public void testUpdateDeviceException() {
        // Arrange
        String deviceId = "TB0001";
        Device updatedDevice = new Device();

        // Mock the service to throw a CustomException when updateDevice is called
        when(deviceService.updateDevice(eq(deviceId), eq(updatedDevice)))
                .thenThrow(new CustomException("Service exception"));

        // Act and Assert
        assertThrows(CustomException.class, () -> deviceRestController.updateDevice(deviceId, updatedDevice));
    }






    // Delete device test

    @Test
    public void testDeleteDevice() {
        // Arrange
        String deviceId = "TB0001";

        // Mock the service to return the existing device
        when(deviceService.getById(deviceId)).thenReturn(Optional.of(new Device()));

        // Act
        ResponseEntity<Void> response = deviceRestController.deleteDevice(deviceId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testDeleteDeviceException() {
        // Arrange
        String deviceId = "TB0001";

        // Mock the service to return the existing device
        when(deviceService.getById(deviceId)).thenReturn(Optional.of(new Device()));

        // Simulér en fejl i servicekaldet, når enheden ikke findes
        when(deviceService.getById(deviceId)).thenThrow(new CustomException("Device not found"));

        // Act and Assert
        assertThrows(CustomException.class, () -> deviceRestController.deleteDevice(deviceId));
    }








}

 */

