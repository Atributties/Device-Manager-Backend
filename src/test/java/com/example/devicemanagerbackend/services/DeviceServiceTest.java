package com.example.devicemanagerbackend.services;

import com.example.devicemanagerbackend.DTO.DeviceDTO;
import com.example.devicemanagerbackend.entities.Device;
import com.example.devicemanagerbackend.exceptions.CustomException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    /*
    @Test
    public void testSaveDevice() {
        // Arrange
        Device device = new Device(
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

        // Mock the behavior of the repository
        when(deviceRepository.save(any(Device.class))).thenReturn(device);

        // Act
        Optional<Device> result = deviceService.saveDevice(device);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(device, result.get());
        assertNotNull(device.getId()); // Ensure the ID is not null
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
        String deviceId = "SP0001";
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
        DeviceDTO updatedDevice = new DeviceDTO();
        updatedDevice.setId("testUpdatedId");

        when(deviceRepository.save(updatedDevice)).thenReturn(updatedDevice);

        when(deviceRepository.findById(eq("testUpdatedId"))).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(CustomException.class, () -> deviceService.updateDevice(updatedDevice.getId(), updatedDevice));

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

     */
}
