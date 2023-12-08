package com.example.devicemanagerbackend.enums;

import com.example.devicemanagerbackend.DTO.StatusDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Status {
    IN_USE("In Use"),
    IN_STORAGE("In Storage"),
    UNDER_REPAIR("Under Repair"),
    RETIRED("Retired");

    private String displayName;

    public StatusDTO toDto() {
        return new StatusDTO(displayName, name());
    }

}
