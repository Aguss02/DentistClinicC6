package com.project.service;


import com.project.dto.PatientDTO;
import com.project.exception.BadRequestException;
import com.project.exception.ResourceNotFoundException;
import com.project.entity.Patient;

import java.util.Set;


public interface PatientService {

     Set<PatientDTO> getAllPatients();
     PatientDTO findPatientById(long id) throws ResourceNotFoundException;
     Patient savePatient(Patient newPatient) throws BadRequestException;
     Patient updatePatient(Patient patient) throws ResourceNotFoundException, BadRequestException;
     void deletePatientById(long id) throws ResourceNotFoundException;

}