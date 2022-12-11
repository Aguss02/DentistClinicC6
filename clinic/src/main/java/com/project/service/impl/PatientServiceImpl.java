package com.project.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.dto.PatientDTO;
import com.project.exception.BadRequestException;
import com.project.exception.ResourceNotFoundException;
import com.project.model.Patient;
import com.project.repository.PatientRepository;
import com.project.service.PatientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;
    private final ObjectMapper mapper;
    private static final Logger LOGGER = Logger.getLogger(PatientServiceImpl.class);

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, ObjectMapper mapper) {
        this.patientRepository = patientRepository;
        this.mapper = mapper;
    }

    @Override
    public Set<PatientDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();

        Set<PatientDTO> patientDTOS = new HashSet<>();

        for(Patient patient: patients){
            patientDTOS.add(mapper.convertValue(patient, PatientDTO.class));
        }

        return patientDTOS;
    }

    @Override
    public PatientDTO findPatientById(long id) throws ResourceNotFoundException {
        // It can bring Dentist or NULL
        Optional<Patient> patient = patientRepository.findById(id);

        // Create container for DTO
        PatientDTO patientDTO;

        if (patient.isEmpty()){
            LOGGER.error("Patient with Id " + id + " was not found");
            throw new ResourceNotFoundException("Patient with Id " + id + " was not found");
        } else {
            patientDTO = mapper.convertValue(patient, PatientDTO.class);
        }

        return patientDTO;
    }


    @Override
    public Patient savePatient(Patient newPatient) throws BadRequestException {

        if (newPatient == null) {
            LOGGER.error("Patient is null");
            throw new BadRequestException("Patient is null");
        }

        patientRepository.save(newPatient);
        LOGGER.info("Patient has been created");
        return newPatient;
    }


    @Override
    public Patient updatePatient(Patient patient) throws ResourceNotFoundException, BadRequestException {

        if (patient == null){
            LOGGER.error("Patient Is Null");
            throw new ResourceNotFoundException("Patient was not found");
        } else if (patient.getId() == null) {
            LOGGER.error("Patient must contain Id in order to update it");
            throw new BadRequestException("Patient must contain Id in order to update it");
        }

        LOGGER.info("Patient with Id " + patient.getId() + " has been updated");
        return patientRepository.save(patient);

    }

    @Override
    public void deletePatientById(long id) throws ResourceNotFoundException {
        if (patientRepository.findById(id).isEmpty()){
            LOGGER.error("Patient with Id " + id + " Was Not Found");
            throw new ResourceNotFoundException("Patient with Id " + id + " was not found");
        }
        LOGGER.info("Patient with Id " + id + " has been deleted");
        patientRepository.deleteById(id);
    }


}
