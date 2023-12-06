package com.example.devicemanagerbackend.controllers;

import com.example.devicemanagerbackend.DTO.DatacardDTO;
import com.example.devicemanagerbackend.DTO.DeviceDTO;
import com.example.devicemanagerbackend.entities.Datacard;
import com.example.devicemanagerbackend.entities.Device;
import com.example.devicemanagerbackend.exceptions.CustomException;
import com.example.devicemanagerbackend.services.DatacardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/datacard")
@CrossOrigin("*")
public class DatacardController {

    private final DatacardService datacardService;


    public DatacardController(DatacardService datacardService) {
        this.datacardService = datacardService;
    }

    @GetMapping
    public ResponseEntity<List<Datacard>> getAllDatacards() {
        List<Datacard> datacards = datacardService.findAll();
        return ResponseEntity.ok(datacards);
    }

    // Hent en specifik enhed efter ID
    @GetMapping("/{id}")
    public ResponseEntity<Datacard> getDatacardById(@PathVariable int id) {
        return datacardService.getById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CustomException("Datacard not found with id: " + id));
    }

    @PostMapping
    public ResponseEntity<Datacard> createDatacard(@RequestBody Datacard datacard) {
        return datacardService.saveDatacard(datacard)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CustomException("Problem with post Datacard"));
    }



    // Opdater en eksisterende enhed
    @PutMapping("/{id}")
    public ResponseEntity<Datacard> updateDatacard(@PathVariable int id, @RequestBody DatacardDTO updatedDatacard) {
        Datacard updated = datacardService.updateDatacard(id, updatedDatacard);
        return ResponseEntity.ok(updated);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletDatacard(@PathVariable int id) {
        return datacardService.getById(id)
                .map(datacard -> {
                    datacardService.delete(datacard);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseThrow(() -> new CustomException("Datacard not found with ID: " + id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Datacard>> getDatacardsByUserId(@PathVariable int userId) {
        List<Datacard> datacards = datacardService.findDatacardsByUserId(userId);
        return ResponseEntity.ok(datacards);
    }

}
