package fr.microservices.patients.repository;

import fr.microservices.patients.entity.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {

    List<Patient> findAllByIdInfirmier(String id);
}
