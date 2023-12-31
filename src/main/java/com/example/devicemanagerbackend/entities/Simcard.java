package com.example.devicemanagerbackend.entities;

import com.example.devicemanagerbackend.enums.Status;
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
    private int id;



    @Column(nullable = false)
    private String iccidNumber;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String pin;

    @Column(nullable = false)
    private String puk;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
