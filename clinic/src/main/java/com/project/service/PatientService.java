package com.project.service;


import com.project.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {

    public List<Patient> getAllPatients();
    public Optional<Patient> findPatientById(long id);
    public Patient addPatient(Patient newPatient);
    public String deletePatient(long id);
    public String updatePatient(long id, Patient patient);
}
