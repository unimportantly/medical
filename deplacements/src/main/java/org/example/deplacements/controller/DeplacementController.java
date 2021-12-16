package org.example.deplacements.controller;

import org.example.deplacements.dto.DeplacementDTO;
import org.example.deplacements.service.DeplacementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/deplacement")
public class DeplacementController {

    private DeplacementService deplacementService;

    public DeplacementController(DeplacementService deplacementService) {
        this.deplacementService = deplacementService;
    }

    @GetMapping()
    public List<DeplacementDTO> getAllDeplacements(){
        return deplacementService.getAllDeplacements();
    }

    @GetMapping("/{id}")
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
}
