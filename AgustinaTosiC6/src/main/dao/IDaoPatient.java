package main.dao;

import main.model.Patient;

public interface IDaoPatient {

    void createTablePatient();

    void addPatient(Patient patient);

    void showPatients();

    String updatePatient(Patient patient);

    String deletePatient(Patient patient);

}
