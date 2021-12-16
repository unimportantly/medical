package org.example.deplacements.controller;

import org.example.deplacements.dto.DeplacementDTO;
import org.example.deplacements.service.DeplacementService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
