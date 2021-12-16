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

    // calls the service singleton and injects it inside a constructor
    private DeplacementService deplacementService;

    public DeplacementController(DeplacementService deplacementService) {
        this.deplacementService = deplacementService;
    }

    /**
     * function to get a list of all appointments
     * @return a list of all appointments
     */
    @GetMapping()
    public List<DeplacementDTO> getAllDeplacements(){
        return deplacementService.getAllDeplacements();
    }

    /**
     * function to find a single appointment thanks to its id
     * @param id the String that points to the appointment in the database
     * @return a response entity of the appointment
     */
    @GetMapping("{id}")
    public ResponseEntity<DeplacementDTO> getDeplacement(@PathVariable String id){
        DeplacementDTO deplacementDTO = deplacementService.getDeplacement(id);
        if(deplacementDTO == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(deplacementDTO);
    }

    /**
     * a function to list all future appointments
     * @return a list of future appointments
     */
    @GetMapping("/next")
    public List<DeplacementDTO> getNextDeplacements(){
        return deplacementService.getNextDeplacements();
    }

    /**
     * function to get a list of a patient's future appointments
     * @param id the String that points to the patient in the database
     * @return a list of the patient's next appointments
     */
    @GetMapping("/patients/{id}")
    public List<DeplacementDTO> getNextPatientDeplacements(@PathVariable String id){
        return deplacementService.getNextPatientDeplacements(id);
    }

    /**
     * function to get a list of a nurse's future appointments
     * @param id the String that points to the nurse in the database
     * @return a list of the nurse's next appointments
     */
    @GetMapping("/infirmiers/{id}")
    public List<DeplacementDTO> getNextInfirmierDeplacements(@PathVariable String id){
        return deplacementService.getNextInfirmierDeplacement(id);
    }

    /**
     * function to display an appointment's client's and nurse's first & last names
     * as well as their ids
     * @param id the String that points to the appointment in the database
     * @return a response entity containing the appointment
     */
    @GetMapping("/deplacement/{id}")
    public ResponseEntity<DeplacementDetailsDTO> getDetails(@PathVariable String id){
        DeplacementDetailsDTO deplacementDetailsDTO = deplacementService.getDetails(id);
        if(deplacementDetailsDTO == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(deplacementDetailsDTO);
    }

    /**
     * function to create an appointment inside the database
     * @param deplacementDTO the appointment to put in the db
     * @return a representation of the db entity
     */
    @PostMapping()
    public ResponseEntity<DeplacementDTO> save(@RequestBody DeplacementDTO deplacementDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(deplacementService.save(deplacementDTO));
    }

    /**
     * function to update an appointment inside the database
     * @param deplacementDTO the appointment to update in the db
     * @return a representation of the db entity
     */
    @PutMapping()
    public ResponseEntity<DeplacementDTO> update(@RequestBody DeplacementDTO deplacementDTO){
        return ResponseEntity.ok(deplacementService.update(deplacementDTO));
    }

    /**
     * function to delete an entity from the server
     * @param id the id of the object to be deleted
     */
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        deplacementService.delete(id);
    }
}
