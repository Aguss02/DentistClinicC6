package com.project.controller;

import com.project.model.Dentist;
import com.project.repository.DentistRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dentist")
public class DentistController {

    @Autowired
    private DentistRepository dentistRepository;
    private static final Logger LOGGER = Logger.getLogger(DentistController.class);

    @GetMapping()
    public List<Dentist> getDentists(){
        return dentistRepository.findAll();
    }

    @PostMapping("/add")
    public void addDentist(@RequestBody Dentist patient){
        dentistRepository.save(patient);
        LOGGER.info("Dentist added");
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDentist(@PathVariable long id) {
        Dentist dentist = dentistRepository.findById(id).get();
        dentistRepository.delete(dentist);
        LOGGER.info("Dentist deleted");
    }

    @PutMapping("/update/{id}")
    public void modifyDentist(@PathVariable long id, @RequestBody Dentist dentist){
        Dentist updateDentist = dentistRepository.findById(id).get();
        updateDentist.setSurname(dentist.getSurname());
        updateDentist.setName(dentist.getName());
        updateDentist.setMedicalLicense(dentist.getMedicalLicense());
        dentistRepository.save(updateDentist);
        LOGGER.info("Dentist updated");
    }

}
