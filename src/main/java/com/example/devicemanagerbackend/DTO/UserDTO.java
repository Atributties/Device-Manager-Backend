package com.example.devicemanagerbackend.DTO;

import com.example.devicemanagerbackend.entities.User;
import com.example.devicemanagerbackend.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int id;
    private String firstname;
    private String middlename;
    private String lastname;
    private String email;
    private UserRole userRole;
    private Collection<? extends GrantedAuthority> authorities;
    private Set<DeviceDTO> devices;
    private Set<DatacardDTO> datacards;
    private Set<SimcardDTO> simcards;

    public UserDTO(User user) {
        this.id = user.getId();
        this.firstname = user.getFirstname();
        this.middlename = user.getMiddlename();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.userRole = user.getUserRole();
        this.authorities = user.getAuthorities();
        this.devices = user.getDevices().stream().map(DeviceDTO::new).collect(Collectors.toSet());
        this.datacards = user.getDatacards().stream().map(DatacardDTO::new).collect(Collectors.toSet());
        this.simcards = user.getSimcards().stream().map(SimcardDTO::new).collect(Collectors.toSet());

    }


}
