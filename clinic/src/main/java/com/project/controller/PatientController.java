package com.project.controller;

import com.project.dto.PatientDTO;
import com.project.exception.BadRequestException;
import com.project.exception.ResourceNotFoundException;
import com.project.model.Patient;
import com.project.service.impl.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientServiceImpl patientService;

    @Autowired
    public PatientController(PatientServiceImpl patientService) {
        this.patientService = patientService;
    }

    @GetMapping()
    public Set<PatientDTO> getPatients(){
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public PatientDTO getPatientById(@PathVariable long id) throws ResourceNotFoundException {
        return patientService.findPatientById(id);
    }


    @PostMapping("/add")
    public ResponseEntity<?> savePatient(@RequestBody Patient newPatient) throws BadRequestException {
        patientService.savePatient(newPatient);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePatients(@PathVariable long id) throws ResourceNotFoundException {
        patientService.deletePatientById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> modifyPatient(@RequestBody Patient patient) throws ResourceNotFoundException, BadRequestException {
        patientService.updatePatient(patient);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
