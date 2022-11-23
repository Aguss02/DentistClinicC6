package com.project.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Table
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String surname;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private Integer dni;
    @Column
    private LocalDate registrationDate;
    // @OneToOne(mappedBy = "Patient", fetch = FetchType.LAZY)
    // private Appointment appointment;

}
