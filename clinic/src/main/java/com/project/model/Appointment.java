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
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDate date;
   // @OneToOne(mappedBy = "Appointment", fetch = FetchType.LAZY)
   // @JoinColumn(name = "patient", referencedColumnName = "id")
   // private Patient patient;

}
