package main.model;

import org.apache.log4j.Logger;

public class Dentist {

    private Integer id;
    private String surname;
    private String name;
    private String medicalLicense;

    public Dentist(){

    }

    public Dentist(Integer id, String surname, String name, String medicalLicense) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.medicalLicense = medicalLicense;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMedicalLicense() {
        return medicalLicense;
    }

    public void setMedicalLicense(String medicalLicense) {
        this.medicalLicense = medicalLicense;
    }
}
