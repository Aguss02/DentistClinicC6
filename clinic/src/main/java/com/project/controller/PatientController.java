package com.project.controller;

import com.project.model.Patient;
import com.project.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;
    private static final Logger LOGGER = Logger.getLogger(PatientController.class);

    @GetMapping()
    public List<Patient> getPatients(){
        return patientRepository.findAll();
    }

    @PostMapping("/add")
    public void addPatient(@RequestBody Patient patient){
        patientRepository.save(patient);
        LOGGER.info("Patient added");
    }

    @DeleteMapping("/delete/{id}")
    public void deletePatients(@PathVariable long id) {
        Patient patient = patientRepository.findById(id).get();
        patientRepository.delete(patient);
        LOGGER.info("Patient deleted");
    }

    @PutMapping("/update/{id}")
    public void modifyPatient(@PathVariable long id, @RequestBody Patient patient){
        Patient updatePatient = patientRepository.findById(id).get();
        updatePatient.setSurname(patient.getSurname());
        updatePatient.setName(patient.getName());
        updatePatient.setAddress(patient.getAddress());
        updatePatient.setDni(patient.getDni());
        updatePatient.setRegistrationDate(patient.getRegistrationDate());
        patientRepository.save(updatePatient);
        LOGGER.info("Patient updated");
    }

}
