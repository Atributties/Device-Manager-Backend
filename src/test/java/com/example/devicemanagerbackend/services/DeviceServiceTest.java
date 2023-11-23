package com.example.devicemanagerbackend.services;

import com.example.devicemanagerbackend.entities.Device;
import com.example.devicemanagerbackend.repositories.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;


    @InjectMocks
    private DeviceService deviceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveDevice() {

        // Arrange
        Device device = new Device();
        int nextId = 123213;

        // Mocking the behavior of the repository
        when(deviceRepository.save(any(Device.class))).thenAnswer(invocation -> {
            Device savedDevice = invocation.getArgument(0);
            savedDevice.setId(nextId); // Assign a mock ID for testing purposes
            return savedDevice;
        });

        // Act
        Optional<Device> result = deviceService.saveDevice(device);

        // Assert
        assertEquals(Optional.of(device), result);
        assertEquals(nextId, device.getId());
    }

    @Test
    public void testFindAllDevices() {
        // Arrange
        List<Device> expectedDevices = new ArrayList<>();
        when(deviceRepository.findAll()).thenReturn(expectedDevices);

        // Act
        List<Device> result = deviceService.findAll();

        // Assert
        assertEquals(expectedDevices, result);
    }

    @Test
    public void testGetDeviceById() {
        // Arrange
        String deviceId = "DEVICE001";
        Device expectedDevice = new Device();

        when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(expectedDevice));

        // Act
        Optional<Device> result = deviceService.getById(deviceId);

        // Assert
        assertEquals(Optional.of(expectedDevice), result);
    }

    @Test
    public void testUpdateDevice() {
        // Arrange
        Device updatedDevice = new Device();

        when(deviceRepository.save(updatedDevice)).thenReturn(updatedDevice);

        // Act
        Device result = deviceService.updateDevice(updatedDevice);

        // Assert
        assertEquals(updatedDevice, result);
    }

    @Test
    public void testDeleteDevice() {
        // Arrange
        Device device = new Device();

        // Act
        deviceService.delete(device);

        // Assert
        verify(deviceRepository, times(1)).delete(device);
    }
}
