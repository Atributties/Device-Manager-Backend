package com.example.devicemanagerbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Datacard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "datacard_id")
    private int id;


    @Column(nullable = false)
    private String iccidNumber;

    @Column(nullable = false)
    private String pin;

    @Column(nullable = false)
    private String puk;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id") // This is the foreign key column in the Device table
    private User user;

}
