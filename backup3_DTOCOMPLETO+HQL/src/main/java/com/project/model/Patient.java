package com.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@ToString
@Table
public class Patient {

    @Id
    @SequenceGenerator(name = "patient_sequence", sequenceName = "patient_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_sequence")
    private Long id;
    private String surname;
    private String name;
    private String address;
    private Integer dni;
    private Date registrationDate;
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Appointment> appointments;

    // Constructor Empty
   public Patient(){

   }

    // Constructor with every property
    public Patient(Long id, String surname, String name, String address, Integer dni, Date registrationDate, List<Appointment> appointments) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.address = address;
        this.dni = dni;
        this.registrationDate = registrationDate;
        this.appointments = appointments;
    }

    // Constructor without appointments
    public Patient(Long id, String surname, String name, String address, Integer dni, Date registrationDate) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.address = address;
        this.dni = dni;
        this.registrationDate = registrationDate;
    }

    // Constructor without appointments and id
    public Patient(String surname, String name, String address, Integer dni, Date registrationDate) {
        this.surname = surname;
        this.name = name;
        this.address = address;
        this.dni = dni;
        this.registrationDate = registrationDate;
    }

}
