package com.project.controller;

import com.project.dto.PatientDTO;
import com.project.exception.BadRequestException;
import com.project.exception.ResourceNotFoundException;
import com.project.model.Patient;
import com.project.service.impl.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;

import java.util.Set;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientServiceImpl patientService;

    public PatientController(PatientServiceImpl patientService) {
        this.patientService = patientService;
    }

    @GetMapping()
    public Set<PatientDTO> getPatients(){
        return patientService.getAllPatients();
    }

    @PostMapping("/add")
    public Patient savePatient(@RequestBody Patient patient) throws BadRequestException {
        return patientService.savePatient(patient);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePatients(@PathVariable long id) throws ResourceNotFoundException {
        patientService.deletePatientById(id);
    }

    @PutMapping("/update/{id}")
    public Patient modifyPatient(@RequestBody Patient patient) throws ResourceNotFoundException, BadRequestException {
        return patientService.updatePatient(patient);
    }

}
