package com.project.controller;

import com.project.model.Patient;
import com.project.service.impl.PatientServiceImpl;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientServiceImpl patientService;
    private static final Logger LOGGER = Logger.getLogger(PatientController.class);

    public PatientController(PatientServiceImpl patientService) {
        this.patientService = patientService;
    }

    @GetMapping()
    public List<Patient> getPatients(){
        return patientService.getAllPatients();
    }

    @PostMapping("/add")
    public Patient addPatient(@RequestBody Patient patient){
        return patientService.addPatient(patient);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePatients(@PathVariable long id) {
        patientService.deletePatient(id);
    }

    @PutMapping("/update/{id}")
    public void modifyPatient(@PathVariable long id, @RequestBody Patient patient){
        patientService.updatePatient(id, patient);
    }

}
