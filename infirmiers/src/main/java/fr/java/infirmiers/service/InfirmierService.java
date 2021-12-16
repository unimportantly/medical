package fr.java.infirmiers.service;

import fr.java.infirmiers.entity.Infirmier;
import fr.java.infirmiers.entity.InfirmierPatient;
import fr.java.infirmiers.entity.Patient;
import fr.java.infirmiers.repository.InfirmierRepository;
import fr.java.infirmiers.repository.PatientRepository;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InfirmierService {

    private InfirmierRepository infirmierRepository;
    private PatientRepository patientRepository;

    public InfirmierService(InfirmierRepository infirmierRepository, PatientRepository patientRepository) {
        this.infirmierRepository = infirmierRepository;
        this.patientRepository = patientRepository;
    }

    public InfirmierPatient getPatients(String idInfirmier){
        Optional<Infirmier> infirmier = infirmierRepository.findById(idInfirmier);
        InfirmierPatient infirmierPatient = null;
        if(infirmier.isPresent()){
            Mono<List<Patient>> patientsMono = patientRepository.findAll(idInfirmier).collectList();
//            patientsMono.subscribe(patients::addAll);
//            infirmierPatient = new InfirmierPatient(infirmier.get(), patients);
//            patientRepository.findAll(idInfirmier).subscribe(patients::add);
            infirmierPatient = new InfirmierPatient(infirmier.get(), patientsMono.block());
        }
        return infirmierPatient;
    }

    public List<Infirmier> getInfirmiers(){
        return infirmierRepository.findAll();
    }

    public Optional<Infirmier> findById(String id){
        return infirmierRepository.findById(id);
    }

    public Infirmier save(Infirmier infirmier){
        return infirmierRepository.save(infirmier);
    }

    public Infirmier update(Infirmier infirmier){
        return infirmierRepository.save(infirmier);
    }

    public void delete(Infirmier infirmier){
        infirmierRepository.delete(infirmier);
    }
}
