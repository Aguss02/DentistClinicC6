package com.project.service;


import com.project.dto.PatientDTO;
import com.project.exception.BadRequestException;
import com.project.exception.NotFoundException;
import com.project.model.Patient;
import java.util.Set;


public interface PatientService {

     Set<PatientDTO> getAllPatients();
     PatientDTO findPatientById(long id) throws NotFoundException;
     Patient savePatient(Patient newPatient) throws BadRequestException;
     Patient updatePatient(Patient patient) throws NotFoundException, BadRequestException;
     void deletePatient(long id) throws NotFoundException;

}