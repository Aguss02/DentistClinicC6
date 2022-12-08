package com.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table
public class Dentist {

    @Id
    @SequenceGenerator(name = "dentist_sequence", sequenceName = "dentist_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dentist_sequence")
    private Long id;

    private String surname;
    private String name;
    private String license;

    @OneToMany(mappedBy = "dentist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Appointment> appointments;

    // Empty Constructor
    public Dentist() {
    }

    // Constructor with every property
    public Dentist(Long id, String surname, String name, String license, List<Appointment> appointments) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.license = license;
        this.appointments = appointments;
    }

    // Constructor without appointments
    public Dentist(Long id, String surname, String name, String license) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.license = license;
    }

    // Constructor without id and appointments
    public Dentist(String surname, String name, String license) {
        this.surname = surname;
        this.name = name;
        this.license = license;
    }
}
