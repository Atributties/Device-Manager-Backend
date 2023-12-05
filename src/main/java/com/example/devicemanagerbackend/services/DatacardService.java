package com.example.devicemanagerbackend.services;

import com.example.devicemanagerbackend.DTO.DatacardDTO;
import com.example.devicemanagerbackend.DTO.DeviceDTO;
import com.example.devicemanagerbackend.entities.Datacard;
import com.example.devicemanagerbackend.entities.Device;
import com.example.devicemanagerbackend.entities.User;
import com.example.devicemanagerbackend.enums.DeviceStatus;
import com.example.devicemanagerbackend.exceptions.CustomException;
import com.example.devicemanagerbackend.repositories.DatacardRepository;
import com.example.devicemanagerbackend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DatacardService {

    @Autowired
    DatacardRepository datacardRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public Optional<Datacard> saveDatacard(Datacard datacard) {
        datacardRepository.save(datacard);
        return Optional.of(datacard);
    }

    public List<Datacard> findAll() {
        return datacardRepository.findAll();
    }

    public Optional<Datacard> getById(int id) {
        return datacardRepository.findById(id);
    }

    @Transactional
    public Datacard updateDatacard(int id, DatacardDTO updateDatacard) {
        return datacardRepository.findById(id)
                .map(existingDatacard -> {
                    existingDatacard.setIccidNumber(updateDatacard.getIccidNumber());
                    existingDatacard.setPin(updateDatacard.getPin());
                    existingDatacard.setPuk(updateDatacard.getPuk());

                    // Check if user is assigned
                    if (updateDatacard.getUser() != null) {
                        int userId = updateDatacard.getUser().getId();
                        if (userId != 0) {
                            // Assign the device to the user
                            User user = userRepository.findById(userId)
                                    .orElseThrow(() -> new CustomException("User not found with id: " + userId));
                            existingDatacard.setUser(user);

                        }
                    } else {
                        // User is not assigned, set user to null and update device status
                        existingDatacard.setUser(null);

                    }
                    return datacardRepository.save(existingDatacard);
                })
                .orElseThrow(() -> new CustomException("device not updated"));
    }

    public void delete(Datacard datacard) {
        datacardRepository.delete(datacard);
    }


}
