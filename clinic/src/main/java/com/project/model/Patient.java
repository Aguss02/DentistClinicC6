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
    @Column(nullable = false, length = 45)
    private String surname;
    @Column(nullable = false, length = 45)
    private String name;
    @Column(nullable = false, length = 45)
    private String address;
    @Column(nullable = false, unique = true, length = 45)
    private Integer dni;
    @Column
    private LocalDate registrationDate;
    // @OneToOne(mappedBy = "Patient", fetch = FetchType.LAZY)
    // private Appointment appointment;

}
