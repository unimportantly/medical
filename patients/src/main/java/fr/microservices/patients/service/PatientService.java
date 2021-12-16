package fr.microservices.patients.service;

import fr.microservices.patients.entity.InfirmierDTO;
import fr.microservices.patients.entity.Patient;
import fr.microservices.patients.entity.PatientInfirmier;
import fr.microservices.patients.repository.InfirmierRepository;
import fr.microservices.patients.repository.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class PatientService {

    private PatientRepository patientRepository;
    private InfirmierRepository infirmierRepository;

    public PatientService(PatientRepository patientRepository, InfirmierRepository infirmierRepository) {
        this.patientRepository = patientRepository;
        this.infirmierRepository = infirmierRepository;
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public PatientInfirmier findById(String id) {
        Optional<Patient> p = patientRepository.findById(id);
        PatientInfirmier patientInfirmier = null;
        if(p.isPresent()) {
            Mono<InfirmierDTO> infirmier = infirmierRepository.getInfirmier(p.get().getIdInfirmier());
            patientInfirmier = new PatientInfirmier(infirmier.block(), p.get());
        }
        return patientInfirmier;
    }
    // link to the repo to link to the infirmier
    // then store it all in a DTO

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient update(Patient patient) {
        return patientRepository.save(patient);
    }

    public void delete(Patient patient) {
        patientRepository.delete(patient);
    }

    public List<Patient> findAllByIdInfirmier(String id) {
        return patientRepository.findAllByIdInfirmier((id));
    }
}
