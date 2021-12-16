package fr.java.infirmiers.controller;

import fr.java.infirmiers.entity.Infirmier;
import fr.java.infirmiers.entity.InfirmierPatient;
import fr.java.infirmiers.entity.Patient;
import fr.java.infirmiers.service.InfirmierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin // -> this will
@RequestMapping("/infirmiers")
public class InfirmierController {

    InfirmierService infirmierService;

    public InfirmierController(InfirmierService infirmierService) {
        this.infirmierService = infirmierService;
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Infirmier> findAll(){
        return infirmierService.getInfirmiers();
    }

    @GetMapping("/infirmier/{id}")
    public InfirmierPatient getPatients(@PathVariable String id) {
        return infirmierService.getPatients(id);
    }

    @GetMapping("{id}")
    public Optional<Infirmier> findById(@PathVariable String id){
        return infirmierService.findById(id);
    }

    @PostMapping()
    public Infirmier save(@RequestBody Infirmier infirmier){
        return infirmierService.save(infirmier);
    }

    @PutMapping()
    public Infirmier update(@RequestBody Infirmier infirmier){
        return infirmierService.update(infirmier);
    }

    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestBody Infirmier infirmier){
        infirmierService.delete(infirmier);
        return ResponseEntity.ok("user deleted");
    }
}
