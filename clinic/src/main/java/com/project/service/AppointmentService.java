package com.project.service;

import com.project.dto.AppointmentDTO;
import com.project.exception.BadRequestException;
import com.project.exception.ResourceNotFoundException;
import com.project.model.Appointment;

import java.util.Set;

public interface AppointmentService {

    Appointment saveAppointment(Appointment appointment) throws ResourceNotFoundException, BadRequestException;
    AppointmentDTO findAppointmentById(Long id) throws ResourceNotFoundException;
    Set<AppointmentDTO> getAllAppointments();
    Appointment updateAppointment(Appointment appointment) throws BadRequestException;
    void deleteAppointmentById(Long id) throws ResourceNotFoundException;


}
