package com.example.devicemanagerbackend.services.idService;

import com.example.devicemanagerbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserIdService {

    @Autowired
    private UserRepository userRepository;

    public String generateNextEmployeeId(String userTypeAbbrevation) {
        String maxUserId = userRepository.findMaxUserId();

        maxUserId = (maxUserId != null) ? maxUserId : "";

        int nextSequence = 1;
        if (!maxUserId.isEmpty()){
            int maxSequence = Integer.parseInt(maxUserId.substring(userTypeAbbrevation.length()));
            nextSequence = maxSequence +1;
        }

        String formattedSquence = String.format("%04d", nextSequence);

        return userTypeAbbrevation + formattedSquence;

    }

}
