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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DeplacementService {

    /*
    declares the repositories as attributes to be injected into a constructor
     */
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

    /**
     * function to get all appointments from the database
     * maps the entity to a DTO before adding it into a list
     * @return a list of appointments
     */
    public List<DeplacementDTO> getAllDeplacements() {
        List<DeplacementDTO> deplacementDTOList = new ArrayList<>();
        deplacementRepository.findAll().forEach(deplacement ->
                deplacementDTOList.add(this.modelMapper.map(deplacement, DeplacementDTO.class)));
        return deplacementDTOList;
    }

    /**
     * function to get a single appointment from the database
     * checks if the appointment was there in which case maps it to a DTO
     * @param id String pointing to the appointment
     * @return an appointment
     */
    public DeplacementDTO getDeplacement(String id) {
        Optional<Deplacement> deplacement = deplacementRepository.findById(id);
        DeplacementDTO deplacementDTO = null;
        if(deplacement.isPresent())
            deplacementDTO = this.modelMapper.map(deplacement.get(), DeplacementDTO.class);
        return deplacementDTO;
    }

    /**
     * function to create a list of all future appointments(checks them against the current time)
     * then add them to the list after mapping them
     * @return a list of appointments
     */
    public List<DeplacementDTO> getNextDeplacements() {
        List<DeplacementDTO> deplacementDTOList = new ArrayList<>();
        Date date = Date.from(Instant.now());
        deplacementRepository.findAll().forEach(deplacement -> {
            if(deplacement.getDate().after(date))
                deplacementDTOList.add(this.modelMapper.map(deplacement, DeplacementDTO.class));
        });
        return deplacementDTOList;
    }

    /**
     * function to return all the future appointments of a patient
     * checks against the patient ID and the current time
     * map the appointment to a DTO before adding it to a list
     * @param id patient id
     * @return a list of patient appointments
     */
    public List<DeplacementDTO> getNextPatientDeplacements(String id) {
        List<DeplacementDTO> deplacementDTOList = new ArrayList<>();
        Date date = Date.from(Instant.now());
        deplacementRepository.findAll().forEach(deplacement -> {
            if(deplacement.getIdPatient().equals(id) && deplacement.getDate().after(date))
                    deplacementDTOList.add(this.modelMapper.map(deplacement, DeplacementDTO.class));
        });
        return deplacementDTOList;
    }

    /**
     * function to return all the future appointments of a nurse
     * checks against the nurse ID and the current time
     * map the appointment to a DTO before adding it to a list
     * @param id nurse id
     * @return a list of nurse appointments
     */
    public List<DeplacementDTO> getNextInfirmierDeplacement(String id) {
        List<DeplacementDTO> deplacementDTOList = new ArrayList<>();
        Date date = Date.from(Instant.now());
        deplacementRepository.findAll().forEach(deplacement -> {
            if(deplacement.getIdInfirmier().equals(id) && deplacement.getDate().after(date))
                deplacementDTOList.add(this.modelMapper.map(deplacement, DeplacementDTO.class));
        });
        return deplacementDTOList;
    }

    /**
     * function to display an appointment with the first & last name of the patient & nurse
     * @param id id of the appointment
     * @return an appointment DTO
     */
    public DeplacementDetailsDTO getDetails(String id) {
        Optional<Deplacement> deplacement = this.deplacementRepository.findById(id);
        DeplacementDetailsDTO deplacementDetailsDTO = null;
        if(deplacement.isPresent()){
            Mono<Infirmier> infirmier = infirmierRepository.getInfirmierDetails(deplacement.get().getIdInfirmier());
            Mono<Patient> patient = patientRepository.getPatientDetails(deplacement.get().getIdPatient());
            deplacementDetailsDTO = this.modelMapper.map(deplacement.get(), DeplacementDetailsDTO.class);
            deplacementDetailsDTO.setInfirmier(infirmier.block());
            deplacementDetailsDTO.setPatient(patient.block());
        }
        return deplacementDetailsDTO;
    }

    /**
     * function to save an appointment in the database
     * @param deplacementDTO the appointment to save
     * @return an appointment object
     */
    public DeplacementDTO save(DeplacementDTO deplacementDTO) {
        deplacementRepository.save(this.modelMapper.map(deplacementDTO, Deplacement.class));
        return this.modelMapper.map(deplacementDTO, DeplacementDTO.class);
    }

    /**
     * function to update an appointment in the database
     * @param deplacementDTO the appointment to update
     * @return an appointment object
     */
    public DeplacementDTO update(DeplacementDTO deplacementDTO) {
        deplacementRepository.save(this.modelMapper.map(deplacementDTO, Deplacement.class));
        return  this.modelMapper.map(deplacementDTO, DeplacementDTO.class);
    }

    /**
     * function to delete an appointment from the database
     * @param id id of the appointment to delete
     */
    public void delete(String id) {
        deplacementRepository.deleteById(id);
    }
}
