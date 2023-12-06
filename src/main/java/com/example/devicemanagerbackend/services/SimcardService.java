package com.example.devicemanagerbackend.services;

import com.example.devicemanagerbackend.DTO.DatacardDTO;
import com.example.devicemanagerbackend.DTO.SimcardDTO;
import com.example.devicemanagerbackend.entities.Datacard;
import com.example.devicemanagerbackend.entities.Simcard;
import com.example.devicemanagerbackend.entities.User;
import com.example.devicemanagerbackend.enums.Status;
import com.example.devicemanagerbackend.exceptions.CustomException;
import com.example.devicemanagerbackend.repositories.SimcardRepository;
import com.example.devicemanagerbackend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SimcardService {

    @Autowired
    SimcardRepository simcardRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public Optional<Simcard> saveSimcard(Simcard simcard) {
        System.out.println(simcard.toString());
        simcardRepository.save(simcard);
        return Optional.of(simcard);
    }

    public List<Simcard> findAll() {
        return simcardRepository.findAll();
    }

    public Optional<Simcard> getById(int id) {
        return simcardRepository.findById(id);
    }

    @Transactional
    public Simcard updateSimcard(int id, SimcardDTO updateSimcard) {
        return simcardRepository.findById(id)
                .map(existingSimcard -> {
                    existingSimcard.setIccidNumber(updateSimcard.getIccidNumber());
                    existingSimcard.setPhoneNumber(updateSimcard.getPhoneNumber());
                    existingSimcard.setPin(updateSimcard.getPin());
                    existingSimcard.setPuk(updateSimcard.getPuk());

                    if (updateSimcard.getUser() != null) {
                        int userId = updateSimcard.getUser().getId();
                        if (userId != 0) {
                            // Assign the device to the user
                            User user = userRepository.findById(userId)
                                    .orElseThrow(() -> new CustomException("User not found with id: " + userId));
                            existingSimcard.setUser(user);
                            existingSimcard.setStatus(Status.IN_USE);

                        }
                    } else {
                        // User is not assigned, set user to null and update device status
                        existingSimcard.setUser(null);
                        existingSimcard.setStatus(Status.IN_STORAGE);

                    }
                    return simcardRepository.save(existingSimcard);
                })
                .orElseThrow(() -> new CustomException("device not updated"));
    }

    public void delete(Simcard simcard) {
        simcardRepository.delete(simcard);
    }

    public List<Simcard> findSimcardsByUserId(int userId) {
        return simcardRepository.findByUserId(userId);
    }
}
