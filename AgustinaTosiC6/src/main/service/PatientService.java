package main.service;

import main.dao.IDaoPatient;
import main.dao.impl.IDaoPatientImplH2;
import main.model.Patient;

public class PatientService {
    IDaoPatient patientDao;

    public PatientService() {
        this.patientDao = new IDaoPatientImplH2();
    }

    public void createTablePatient(){
        patientDao.createTablePatient();
    }

    public void insertPatient(Patient patient){
        patientDao.addPatient(patient);
    }

    public void showPatients(){
        patientDao.showPatients();
    }

    public String updatePatient(Patient patient){
        return patientDao.updatePatient(patient);
    }

    public String deletePatient(Patient patient){
        return patientDao.deletePatient(patient);
    }
}
