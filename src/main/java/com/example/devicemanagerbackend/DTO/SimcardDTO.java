package com.example.devicemanagerbackend.DTO;

import com.example.devicemanagerbackend.entities.Simcard;
import com.example.devicemanagerbackend.entities.User;
import lombok.Data;


@Data
public class SimcardDTO {
    private int id;
    private String iccidNumber;
    private String phoneNumber;
    private String pin;
    private String puk;
    private User user;

    public SimcardDTO(Simcard simcard) {
        this.id = simcard.getId();
        this.iccidNumber = simcard.getIccidNumber();
        this.phoneNumber = simcard.getPhoneNumber();
        this.pin = simcard.getPin();
        this.puk = simcard.getPuk();
        this.user = simcard.getUser();
    }
}
