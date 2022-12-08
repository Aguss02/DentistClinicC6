package com.project.service.impl;

import com.project.model.Patient;
import com.project.repository.PatientRepository;
import com.project.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> findPatientById(long id) {
        return patientRepository.findById(id);
    }

    @Override
    public Patient addPatient(Patient newPatient) {
        if (newPatient != null){
            patientRepository.save(newPatient);
            return newPatient;
        }
        return null;
    }

    @Override
    public String deletePatient(long id) {
        if (patientRepository.findById(id).isPresent()){
            patientRepository.deleteById(id);
            return "Patient with id " + id + " has been deleted";
        }
        return "Patient with id " + id + " not found";
        // Armar la excepcion
    }

    @Override
    public String updatePatient(long id, Patient patient) {
        if (patientRepository.findById(id).isPresent()){
            Patient updatePatient = patientRepository.findById(id).get();
            updatePatient.setSurname(patient.getSurname());
            updatePatient.setName(patient.getName());
            updatePatient.setAddress(patient.getAddress());
            updatePatient.setDni(patient.getDni());
            updatePatient.setRegistrationDate(patient.getRegistrationDate());
            patientRepository.save(updatePatient);
            return "Patient with id " + id + " was updated";
        }
        return "Patient with id " + id + " not found";
        // Armar la excepcion
    }
}
