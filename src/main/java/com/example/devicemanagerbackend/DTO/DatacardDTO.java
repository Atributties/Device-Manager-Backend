package com.example.devicemanagerbackend.DTO;

import com.example.devicemanagerbackend.entities.Datacard;
import com.example.devicemanagerbackend.entities.User;
import lombok.Data;

@Data
public class DatacardDTO {
    private int id;
    private String iccidNumber;
    private String pin;
    private String puk;
    private User user;

    public DatacardDTO(Datacard datacard) {
        this.id = datacard.getId();
        this.iccidNumber = datacard.getIccidNumber();
        this.pin = datacard.getPin();
        this.puk = datacard.getPuk();
        this.user = datacard.getUser();
    }
}