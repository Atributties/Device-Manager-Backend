package com.example.devicemanagerbackend.entities;

import com.example.devicemanagerbackend.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fullName;

    @Column(nullable = false)
    private String firstname;

    private String middleName;

    @Column(nullable = false)
    private String lastname;

    @Enumerated(EnumType.STRING)
    private UserType userType;

}
