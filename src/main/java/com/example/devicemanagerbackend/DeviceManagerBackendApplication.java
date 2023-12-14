package com.example.devicemanagerbackend;

import com.example.devicemanagerbackend.entities.*;
import com.example.devicemanagerbackend.enums.Status;
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
import org.springframework.security.crypto.password.PasswordEncoder;

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

            User user = new User();
            user.setFirstname("Mathias");
            user.setMiddlename("Marcus");
            user.setLastname("Christiansen");
            user.setEmail("Christian@example.com");
            user.setPassword("password1");
            user.setUserRole(UserRole.USER);

            Role userRole = roleRepository.findByAuthority(UserRole.USER.name())
                    .orElseThrow(() -> new RuntimeException("Default role not found: " + UserRole.USER.name()));
            user.setRole(userRole);

            userRepository.save(user);


            /*
            Device device = new Device();
            device.setImeiNumber("123456789012345");
            device.setSerialNumber("ABC123");
            device.setDeviceType(DeviceType.SMARTPHONE);
            device.setDeviceModel("ModelXYZ");
            device.setStatus(Status.IN_STORAGE);
            device.setComments("Test comments");
            deviceService.saveDevice(device);
            System.out.println("______________________" + device.getDeviceType());

            Simcard simcard = new Simcard();
            simcard.setIccidNumber("55544433322211112121");
            simcard.setPhoneNumber("98765432");
            simcard.setPin("4321");
            simcard.setPuk("8765");
            simcard.setStatus(Status.IN_STORAGE);
            simcardService.saveSimcard(simcard);

            // Creating and saving test data for Datacard
            Datacard datacard = new Datacard();
            datacard.setIccidNumber("999888777666555");
            datacard.setPin("9999");
            datacard.setPuk("1111");
            datacard.setStatus(Status.IN_STORAGE);
            datacardService.saveDatacard(datacard);

             */

        };

    }
}