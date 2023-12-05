package com.example.devicemanagerbackend;

import com.example.devicemanagerbackend.entities.*;
import com.example.devicemanagerbackend.enums.DeviceStatus;
import com.example.devicemanagerbackend.enums.DeviceType;
import com.example.devicemanagerbackend.enums.UserRole;
import com.example.devicemanagerbackend.repositories.RoleRepository;
import com.example.devicemanagerbackend.repositories.UserRepository;
import com.example.devicemanagerbackend.services.DatacardService;
import com.example.devicemanagerbackend.services.DeviceService;
import com.example.devicemanagerbackend.services.SimcardService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class DeviceManagerBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeviceManagerBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            DeviceService deviceService,
            SimcardService simcardService,
            DatacardService datacardService
    ) {
        return args -> {
            // Opret roller baseret på UserRole enum'en
            for (UserRole userRole : UserRole.values()) {
                roleRepository.findByAuthority(userRole.name())
                        .orElseGet(() -> roleRepository.save(new Role(userRole.name())));
            }


            User admin = new User();
            admin.setFirstname("Lars");
            admin.setMiddlename("Hans");
            admin.setLastname("Christiansen");
            admin.setEmail("admin@example.com");
            admin.setPassword("password");
            admin.setUserRole(UserRole.SYSTEM_ADMIN);

            Role adminRole = roleRepository.findByAuthority(UserRole.SYSTEM_ADMIN.name())
                    .orElseThrow(() -> new RuntimeException("Default role not found: " + UserRole.SYSTEM_ADMIN.name()));
            admin.setRole(adminRole);

            userRepository.save(admin);


            Device device = new Device();
            device.setImeiNumber("123456789012345");
            device.setSerialNumber("ABC123");
            device.setDeviceType(DeviceType.SMARTPHONE);
            device.setDeviceModel("ModelXYZ");
            device.setDeviceStatus(DeviceStatus.IN_USE);
            device.setComments("Test comments");
            deviceService.saveDevice(device);

            Simcard simcard = new Simcard();
            simcard.setIccidNumber("55544433322211112121");
            simcard.setPhoneNumber("98765432");
            simcard.setPin("4321");
            simcard.setPuk("8765");
            simcardService.saveSimcard(simcard);

            // Creating and saving test data for Datacard
            Datacard datacard = new Datacard();
            datacard.setIccidNumber("999888777666555");
            datacard.setPin("9999");
            datacard.setPuk("1111");
            datacardService.saveDatacard(datacard);

        };

    }
}