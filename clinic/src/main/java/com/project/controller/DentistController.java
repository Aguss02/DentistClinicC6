package com.project.controller;

import com.project.model.Dentist;
import com.project.service.impl.DentistServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dentist")
public class DentistController {

    private final DentistServiceImpl dentistService;
    private static final Logger LOGGER = Logger.getLogger(PatientController.class);

    public DentistController(DentistServiceImpl dentistService) {
        this.dentistService = dentistService;
    }

    @GetMapping()
    public List<Dentist> getDentists(){
        return dentistService.getAllDentists();
    }

    @GetMapping("/{id}")
    public Optional<Dentist> findDentistById(@PathVariable long id) {
        return dentistService.findDentistById(id);
    }

    @PostMapping("/add")
    public Dentist addDentist(@RequestBody Dentist dentist){
       return dentistService.addDentist(dentist);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDentist(@PathVariable long id) {
        return dentistService.deleteDentist(id);
    }

    @PutMapping("/update/{id}")
    public String updateDentist(@PathVariable long id, @RequestBody Dentist dentist){
       return dentistService.updateDentist(id, dentist);
    }

}
