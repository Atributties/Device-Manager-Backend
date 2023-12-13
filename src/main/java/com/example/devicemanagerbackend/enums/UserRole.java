package com.example.devicemanagerbackend.enums;

import com.example.devicemanagerbackend.DTO.UserRolesDTO;
import com.example.devicemanagerbackend.entities.Role;
import com.example.devicemanagerbackend.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum UserRole {
    SYSTEM_ADMIN("System Administrator"),
    DEVICE_ADMIN("Device Administrator"),
    USER("Regular User");

    private String displayName;

    public UserRolesDTO toDto() {
        return new UserRolesDTO(displayName, name());
    }

}
