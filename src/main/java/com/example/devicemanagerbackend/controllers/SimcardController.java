package com.example.devicemanagerbackend.controllers;

import com.example.devicemanagerbackend.entities.Simcard;
import com.example.devicemanagerbackend.exceptions.CustomException;
import com.example.devicemanagerbackend.services.SimcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/simcard")
@CrossOrigin("*")
public class SimcardController {

    @Autowired
    private final SimcardService simcardService;


    public SimcardController(SimcardService simcardService) {
        this.simcardService = simcardService;
    }

    @GetMapping
    public ResponseEntity<List<Simcard>> getAllSimcards() {
        List<Simcard> simcards = simcardService.findAll();
        return ResponseEntity.ok(simcards);
    }

    // Hent en specifik enhed efter ID
    @GetMapping("/{id}")
    public ResponseEntity<Simcard> getSimcardById(@PathVariable int id) {
        return simcardService.getById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CustomException("Datacard not found with id: " + id));
    }

    @PostMapping
    public ResponseEntity<Simcard> createSimcard(@RequestBody Simcard simcard) {
        System.out.println(simcard.toString());
        return simcardService.saveSimcard(simcard)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CustomException("Problem with post Simcard"));
    }



    // Opdater en eksisterende enhed
    @PutMapping("/{id}")
    public ResponseEntity<Simcard> updateSimcard(@PathVariable int id, @RequestBody Simcard updatedSimcard) {
        Simcard updated = simcardService.updateSimcard(id, updatedSimcard);
        return ResponseEntity.ok(updated);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSimcard(@PathVariable int id) {
        return simcardService.getById(id)
                .map(datacard -> {
                    simcardService.delete(datacard);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseThrow(() -> new CustomException("Datacard not found with ID: " + id));
    }
}
