package com.project.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.dto.AppointmentDTO;
import com.project.exception.BadRequestException;
import com.project.exception.ResourceNotFoundException;
import com.project.model.Appointment;
import com.project.repository.AppointmentRepository;
import com.project.service.AppointmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
  public class AppointmentServiceImpl implements AppointmentService {

    private static final Logger LOGGER = Logger.getLogger(AppointmentServiceImpl.class);
    private final DentistServiceImpl dentistService;
    private final PatientServiceImpl patientService;
    private final AppointmentRepository appointmentRepository;
    private ObjectMapper mapper;

    @Autowired
    public AppointmentServiceImpl(DentistServiceImpl dentistService, PatientServiceImpl patientService, AppointmentRepository appointmentRepository, ObjectMapper mapper) {
        this.dentistService = dentistService;
        this.patientService = patientService;
        this.appointmentRepository = appointmentRepository;
        this.mapper = mapper;
    }


    @Override
    public Appointment saveAppointment(Appointment appointment) throws BadRequestException, ResourceNotFoundException {

        if(appointment == null || appointment.getDentist() == null || appointment.getPatient() == null){
            LOGGER.warn("Some required Data is null. You must include Dentist and Patient Id in order to add an Appointment");
            throw new BadRequestException("Some required Data is null. You must include Dentist and Patient Id in order to add an Appointment");
        } else {
            LOGGER.info("Appointment has been saved");
            dentistService.findDentistById(appointment.getDentist().getId());
            patientService.findPatientById(appointment.getPatient().getId());
            return appointmentRepository.save(appointment);
        }
    }

    @Override
    public AppointmentDTO findAppointmentById(Long id) throws ResourceNotFoundException {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        AppointmentDTO appointmentDTO;
        if (appointment.isEmpty()){
            LOGGER.error("The Appointment with id " + id + " was not found");
            throw new ResourceNotFoundException("The Appointment with id " + id + " was not found");
        } else {
            appointmentDTO = mapper.convertValue(appointment, AppointmentDTO.class);
        }
        return appointmentDTO;
    }

    @Override
    public Set<AppointmentDTO> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        Set<AppointmentDTO> appointmentDTOS = new HashSet<>();
        for (Appointment appointment: appointments){
            appointmentDTOS.add(mapper.convertValue(appointment, AppointmentDTO.class));
        }
        return appointmentDTOS;
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) throws BadRequestException {
        if(appointment.getId() == null || appointment.getDentist().getId() == null || appointment.getPatient().getId() == null){
            LOGGER.error("The Appointment should contain the Appointment, Dentist and Patient Id in order to update it");
            throw new BadRequestException("The Appointment should contain the Appointment, Dentist and Patient Id in order to update it");
        }
        LOGGER.info("The Appointment has been updated");
        return  appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointmentById(Long id) throws ResourceNotFoundException {
        if (appointmentRepository.findById(id).isEmpty()){
            LOGGER.error("Appointment with Id " + id + " was not found");
            throw new ResourceNotFoundException("Appointment with Id " + id + " was not found");
        }
        LOGGER.info("Appointment with Id " + id + " was deleted");
        appointmentRepository.deleteById(id);
    }


}
