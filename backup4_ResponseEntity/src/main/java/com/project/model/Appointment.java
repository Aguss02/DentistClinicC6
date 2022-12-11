package com.project.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
@ToString
@Table
public class Appointment {

    @Id
    @SequenceGenerator(name = "appointment_sequence", sequenceName = "appointment_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_sequence")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", nullable = false, referencedColumnName = "id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dentist_id", nullable = false, referencedColumnName = "id")
    private Dentist dentist;

    private Date date;

    // Empty Constructor
    public Appointment() {
    }

    public Appointment(Long id) {
        this.id = id;
    }

    //Constructor with all properties
    public Appointment(Long id, Patient patient, Dentist dentist, Date date) {
        this.id = id;
        this.patient = patient;
        this.dentist = dentist;
        this.date = date;
    }

    //Constructor without id
    public Appointment(Patient patient, Dentist dentist, Date date) {
        this.patient = patient;
        this.dentist = dentist;
        this.date = date;
    }

    // Constructor with Patient and Dentist only
    public Appointment(Patient patient, Dentist dentist) {
        this.patient = patient;
        this.dentist = dentist;
    }


}
