package com.example.devicemanagerbackend.services;

import com.example.devicemanagerbackend.entities.Datacard;
import com.example.devicemanagerbackend.exceptions.CustomException;
import com.example.devicemanagerbackend.repositories.DatacardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DatacardService {

    @Autowired
    DatacardRepository datacardRepository;

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
    public Datacard updateDatacard(int id, Datacard updateDatacard) {
        return datacardRepository.findById(id)
                .map(existingDatacard -> {
                    existingDatacard.setImsiNumber(updateDatacard.getImsiNumber());
                    existingDatacard.setPin(updateDatacard.getPin());
                    existingDatacard.setPuk(updateDatacard.getPuk());

                    return datacardRepository.save(existingDatacard);
                })
                .orElseThrow(() -> new CustomException("device not updated"));
    }

    public void delete(Datacard datacard) {
        datacardRepository.delete(datacard);
    }
}
