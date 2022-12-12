package com.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.entity.Dentist;
import com.project.entity.Patient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentDTO {

    private Long id;

    private Patient patient;

    private Dentist dentist;

    private Date date;

}
