package com.example.devicemanagerbackend.DTO;

import com.example.devicemanagerbackend.entities.Datacard;
import com.example.devicemanagerbackend.entities.User;
import com.example.devicemanagerbackend.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DatacardDTO {
    private int id;
    private String iccidNumber;
    private String pin;
    private String puk;
    private Status status;
    private User user;

    public DatacardDTO(Datacard datacard) {
        this.id = datacard.getId();
        this.iccidNumber = datacard.getIccidNumber();
        this.pin = datacard.getPin();
        this.puk = datacard.getPuk();
        this.status = datacard.getStatus();
        this.user = datacard.getUser();
    }
}