package fr.microservices.patients.controller;

import fr.microservices.patients.entity.Patient;
import fr.microservices.patients.entity.PatientInfirmier;
import fr.microservices.patients.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/patients")
public class PatientController {

    PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Patient> getPatients() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public Optional<Patient> getPatient(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping("/patient/{id}")
    public PatientInfirmier getPatientDetails(@PathVariable String id) {
        return service.findDetailsById(id);
    }

    @GetMapping("/infirmiers/{id}")
    public List<Patient> findAllByIdInfirmier(@PathVariable String id){
        return service.findAllByIdInfirmier(id);
    }

    @PostMapping()
    public Patient create(@RequestBody Patient patient){
        return service.save(patient);
    }

    @PutMapping()
    public Patient update(@RequestBody Patient patient){
        return service.update(patient);
    }

    @DeleteMapping()
    public void delete(@RequestBody Patient patient){
        service.delete(patient);
    }
}
