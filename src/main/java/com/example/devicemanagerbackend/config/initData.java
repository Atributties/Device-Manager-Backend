package com.example.devicemanagerbackend.config;

import com.example.devicemanagerbackend.entities.User;
import com.example.devicemanagerbackend.enums.UserType;
import com.example.devicemanagerbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class initData implements CommandLineRunner {

    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {

        User user1 = new User();
        user1.setFirstname("John");
        user1.setMiddleName("David");
        user1.setLastname("Smith");
        user1.setUserType(UserType.SYSTEM_ADMIN);
        userService.saveUser(user1);

        User user2 = new User();
        user2.setFirstname("Alice");
        user2.setLastname("Johnson");
        user2.setUserType(UserType.DEVICE_ADMIN);
        userService.saveUser(user2);

        User user3 = new User();
        user3.setFirstname("Mathias");
        user3.setLastname("Thomassen");
        user3.setUserType(UserType.USER);
        userService.saveUser(user3);

        User user4 = new User();
        user4.setFirstname("Chrille");
        user4.setLastname("Webster");
        user4.setUserType(UserType.USER);
        userService.saveUser(user4);


    }


}
