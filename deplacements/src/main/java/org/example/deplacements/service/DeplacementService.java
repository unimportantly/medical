package org.example.deplacements.service;

import org.example.deplacements.dto.DeplacementDTO;
import org.example.deplacements.entity.Deplacement;
import org.example.deplacements.repository.DeplacementRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class DeplacementService {

    private DeplacementRepository deplacementRepository;
    private ModelMapper modelMapper;

    public List<DeplacementDTO> getAllDeplacements() {
        List<DeplacementDTO> deplacementDTOList = new ArrayList<>();
        deplacementRepository.findAll().forEach(deplacement ->
                deplacementDTOList.add(this.modelMapper.map(deplacement, DeplacementDTO.class)));
        return deplacementDTOList;
    }

    public DeplacementDTO getDeplacement(String id) {
        Optional<Deplacement> deplacement = deplacementRepository.findById(id);
        DeplacementDTO deplacementDTO = null;
        if(deplacement.isPresent())
            deplacementDTO = this.modelMapper.map(deplacement, DeplacementDTO.class);
        return deplacementDTO;
    }

    public List<DeplacementDTO> getNextDeplacements() {
        List<DeplacementDTO> deplacementDTOList = new ArrayList<>();
        Date date = Date.from(Instant.now());
        deplacementRepository.findAll().forEach(deplacement -> {
            if(deplacement.getDate().after(date))
                deplacementDTOList.add(this.modelMapper.map(deplacement, DeplacementDTO.class));
        });
        return deplacementDTOList;
    }

    public List<DeplacementDTO> getNextPatientDeplacements(String id) {
        List<DeplacementDTO> deplacementDTOList = new ArrayList<>();
        Date date = Date.from(Instant.now());
        deplacementRepository.findAll().forEach(deplacement -> {
            if(deplacement.getIdPatient().equals(id) && deplacement.getDate().after(date))
                    deplacementDTOList.add(this.modelMapper.map(deplacement, DeplacementDTO.class));
        });
        return deplacementDTOList;
    }

    public List<DeplacementDTO> getNextInfirmierDeplacement(String id) {
        List<DeplacementDTO> deplacementDTOList = new ArrayList<>();
        Date date = Date.from(Instant.now());
        deplacementRepository.findAll().forEach(deplacement -> {
            if(deplacement.getIdInfirmier().equals(id) && deplacement.getDate().after(date))
                deplacementDTOList.add(this.modelMapper.map(deplacement, DeplacementDTO.class));
        });
        return deplacementDTOList;
    }
}
