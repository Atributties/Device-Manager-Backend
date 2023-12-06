package com.example.devicemanagerbackend.DTO;

import com.example.devicemanagerbackend.entities.Simcard;
import com.example.devicemanagerbackend.entities.User;
import com.example.devicemanagerbackend.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class SimcardDTO {
    private int id;
    private String phoneNumber;
    private String iccidNumber;
    private String pin;
    private String puk;
    private Status status;
    private User user;

    public SimcardDTO(Simcard simcard) {
        this.id = simcard.getId();
        this.phoneNumber = simcard.getPhoneNumber();
        this.iccidNumber = simcard.getIccidNumber();
        this.pin = simcard.getPin();
        this.puk = simcard.getPuk();
        this.status = simcard.getStatus();
        this.user = simcard.getUser();
    }
}
