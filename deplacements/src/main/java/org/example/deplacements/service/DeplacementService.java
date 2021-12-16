package org.example.deplacements.service;

import org.example.deplacements.dto.DeplacementDTO;
import org.example.deplacements.dto.DeplacementDetailsDTO;
import org.example.deplacements.entity.Deplacement;
import org.example.deplacements.entity.Infirmier;
import org.example.deplacements.entity.Patient;
import org.example.deplacements.repository.DeplacementRepository;
import org.example.deplacements.repository.InfirmierRepository;
import org.example.deplacements.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class DeplacementService {

    private DeplacementRepository deplacementRepository;
    private InfirmierRepository infirmierRepository;
    private PatientRepository patientRepository;
    private ModelMapper modelMapper;


    public DeplacementService(DeplacementRepository deplacementRepository, InfirmierRepository infirmierRepository, PatientRepository patientRepository, ModelMapper modelMapper) {
        this.deplacementRepository = deplacementRepository;
        this.infirmierRepository = infirmierRepository;
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

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

    public DeplacementDetailsDTO getDetails(String id) {
        Optional<Deplacement> deplacement = this.deplacementRepository.findById(id);
        DeplacementDetailsDTO deplacementDetailsDTO = null;
        if(deplacement.isPresent()){
            Mono<Infirmier> infirmier = infirmierRepository.getInfirmierDetails(deplacement.get().getIdInfirmier());
            Mono<Patient> patient = patientRepository.getPatientDetails(deplacement.get().getIdPatient());
            deplacementDetailsDTO = this.modelMapper.map(deplacement, DeplacementDetailsDTO.class);
            deplacementDetailsDTO.setInfirmier(infirmier.block());
            deplacementDetailsDTO.setPatient(patient.block());
        }
        return deplacementDetailsDTO;
    }

    public DeplacementDTO save(DeplacementDTO deplacementDTO) {
        deplacementRepository.save(this.modelMapper.map(deplacementDTO, Deplacement.class));
        return this.modelMapper.map(deplacementDTO, DeplacementDTO.class);
    }

    public DeplacementDTO update(DeplacementDTO deplacementDTO) {
        deplacementRepository.save(this.modelMapper.map(deplacementDTO, Deplacement.class));
        return  this.modelMapper.map(deplacementDTO, DeplacementDTO.class);
    }
}
