package com.example.devicemanagerbackend.services;

import com.example.devicemanagerbackend.entities.Simcard;
import com.example.devicemanagerbackend.exceptions.CustomException;
import com.example.devicemanagerbackend.repositories.SimcardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SimcardService {

    @Autowired
    SimcardRepository simcardRepository;

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
    public Simcard updateSimcard(int id, Simcard updateSimcard) {
        return simcardRepository.findById(id)
                .map(existingDatacard -> {
                    existingDatacard.setIccidNumber(updateSimcard.getIccidNumber());
                    existingDatacard.setPhoneNumber(updateSimcard.getPhoneNumber());
                    existingDatacard.setPin(updateSimcard.getPin());
                    existingDatacard.setPuk(updateSimcard.getPuk());

                    return simcardRepository.save(existingDatacard);
                })
                .orElseThrow(() -> new CustomException("device not updated"));
    }



    public void delete(Simcard simcard) {
        simcardRepository.delete(simcard);
    }
}
