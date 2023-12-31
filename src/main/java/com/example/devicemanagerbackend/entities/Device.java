package com.example.devicemanagerbackend.entities;

import com.example.devicemanagerbackend.enums.Status;
import com.example.devicemanagerbackend.enums.DeviceType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_imei",
                        columnNames = "IMEINumber"),
                @UniqueConstraint(name = "unique_serial_number", columnNames = "SerialNumber")
        }
)
public class Device {

    @Id
    @Column(name = "device_id")
    private String id;


    @Column(nullable = false)
    @Size(min = 15, max = 15)
    private String imeiNumber;

    @Column(nullable = false)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeviceType deviceType;

    @Column(nullable = false)
    private String deviceModel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    private String comments;

    @CreationTimestamp //Hibernate automatic create a timestamp in the database
    private LocalDateTime dateCreated;

    @UpdateTimestamp //Hibernate automatic update this timestamp if there has been implemented a change in the database.
    private LocalDateTime lastUpdated;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}

