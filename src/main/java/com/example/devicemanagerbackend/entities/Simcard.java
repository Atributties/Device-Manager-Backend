package com.example.devicemanagerbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Simcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "simcard_id")
    private int id;

    @Column(nullable = false)
    private String ICCIDNumber;

    @Column(nullable = false)
    private int phoneNumber;

    @Column(nullable = false)
    private int pin;

    @Column(nullable = false)
    private int puk;
}