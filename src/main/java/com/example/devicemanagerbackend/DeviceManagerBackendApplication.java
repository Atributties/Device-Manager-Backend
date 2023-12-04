package com.example.devicemanagerbackend;

import com.example.devicemanagerbackend.entities.*;
import com.example.devicemanagerbackend.enums.DeviceStatus;
import com.example.devicemanagerbackend.enums.DeviceType;
import com.example.devicemanagerbackend.enums.UserType;
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

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DeviceManagerBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeviceManagerBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, DeviceService deviceService, SimcardService simcardService, DatacardService datacardService){
        return args -> {
            if (roleRepository.findByAuthority("SYSTEM_ADMIN").isPresent()) return;
            Role adminRole = roleRepository.save(new Role("SYSTEM_ADMIN"));
            roleRepository.save(new Role("USER"));
            roleRepository.save(new Role("DEVICE_ADMIN"));

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            User admin = new User();
            admin.setFirstname("Lars");
            admin.setMiddlename("Hans");
            admin.setLastname("Christiansen");
            admin.setEmail("admin@example.com");
            admin.setPassword("password");
            admin.setAuthorities(roles);
            admin.setUserType(UserType.SYSTEM_ADMIN);

            userRepository.save(admin);

            Device device = new Device();
            device.setImeiNumber("123456789012345");
            device.setSerialNumber("ABC123");
            device.setDeviceType(DeviceType.SMARTPHONE);
            device.setDeviceModel("ModelXYZ");
            device.setDeviceStatus(DeviceStatus.IN_USE);
            device.setComments("Test comments");
            device.setUser(admin);
            deviceService.saveDevice(device);

            Simcard simcard = new Simcard();
            simcard.setIccidNumber("55544433322211112121");
            simcard.setPhoneNumber("98765432");
            simcard.setPin("4321");
            simcard.setPuk("8765");
            simcard.setUser(admin);
            simcardService.saveSimcard(simcard);

            // Creating and saving test data for Datacard
            Datacard datacard = new Datacard();
            datacard.setIccidNumber("999888777666555");
            datacard.setPin("9999");
            datacard.setPuk("1111");
            datacard.setUser(admin);
            datacardService.saveDatacard(datacard);



        };
    }
}
