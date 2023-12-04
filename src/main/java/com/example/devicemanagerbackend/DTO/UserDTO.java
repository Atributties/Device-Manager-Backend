package com.example.devicemanagerbackend.DTO;

import com.example.devicemanagerbackend.entities.Datacard;
import com.example.devicemanagerbackend.entities.Device;
import com.example.devicemanagerbackend.entities.Simcard;
import com.example.devicemanagerbackend.entities.User;
import com.example.devicemanagerbackend.enums.UserType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {
    private int id;
    private List<DeviceDTO> devices;
    private Simcard simcard;
    private List<Datacard> datacards;
    private String firstname;
    private String middlename;
    private String lastname;
    private String email;
    private String password;
    private UserType userType;

    public UserDTO(User user){
        this.id = user.getId();
        if (user.getDevices() != null){
            this.devices = new ArrayList<>();
            for(Device device : user.getDevices()){
                this.devices.add(new DeviceDTO(device));
            }
        }

    }
}
