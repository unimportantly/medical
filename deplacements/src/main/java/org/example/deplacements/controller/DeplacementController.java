package org.example.deplacements.controller;

import org.example.deplacements.dto.DeplacementDTO;
import org.example.deplacements.dto.DeplacementDetailsDTO;
import org.example.deplacements.service.DeplacementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/deplacements")
public class DeplacementController {

    private DeplacementService deplacementService;

    public DeplacementController(DeplacementService deplacementService) {
        this.deplacementService = deplacementService;
    }

    @GetMapping()
    public List<DeplacementDTO> getAllDeplacements(){
        return deplacementService.getAllDeplacements();
    }

    @GetMapping("{id}")
    public ResponseEntity<DeplacementDTO> getDeplacement(@PathVariable String id){
        DeplacementDTO deplacementDTO = deplacementService.getDeplacement(id);
        if(deplacementDTO == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(deplacementDTO);
    }

    @GetMapping("/next")
    public List<DeplacementDTO> getNextDeplacements(){
        return deplacementService.getNextDeplacements();
    }

    @GetMapping("/patients/{id}")
    public List<DeplacementDTO> getNextPatientDeplacements(@PathVariable String id){
        return deplacementService.getNextPatientDeplacements(id);
    }

    @GetMapping("/infirmiers/{id}")
    public List<DeplacementDTO> getNextInfirmierDeplacements(@PathVariable String id){
        return deplacementService.getNextInfirmierDeplacement(id);
    }

    @GetMapping("/deplacement/{id}")
    public ResponseEntity<DeplacementDetailsDTO> getDetails(@PathVariable String id){
        DeplacementDetailsDTO deplacementDetailsDTO = deplacementService.getDetails(id);
        if(deplacementDetailsDTO == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(deplacementDetailsDTO);
    }

    @PostMapping()
    public ResponseEntity<DeplacementDTO> save(@RequestBody DeplacementDTO deplacementDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(deplacementService.save(deplacementDTO));
    }

    @PutMapping()
    public ResponseEntity<DeplacementDTO> update(@RequestBody DeplacementDTO deplacementDTO){
        return ResponseEntity.ok(deplacementService.update(deplacementDTO));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        deplacementService.delete(id);
    }
}
