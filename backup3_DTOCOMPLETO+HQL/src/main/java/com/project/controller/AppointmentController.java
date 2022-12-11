package com.project.controller;


import com.project.dto.AppointmentDTO;
import com.project.exception.BadRequestException;
import com.project.exception.ResourceNotFoundException;
import com.project.model.Appointment;
import com.project.service.impl.AppointmentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentServiceImpl appointmentService;

    public AppointmentController(AppointmentServiceImpl appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping()
    public Set<AppointmentDTO> getAllAppointments(){
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public AppointmentDTO getAppointmentById(@PathVariable Long id) throws ResourceNotFoundException {
        return appointmentService.findAppointmentById(id);
    }

    @PostMapping("/add")
    public Appointment saveAppointment(@RequestBody Appointment appointment) throws BadRequestException, ResourceNotFoundException {
        return appointmentService.saveAppointment(appointment);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAppointment(@PathVariable long id) throws ResourceNotFoundException {
        appointmentService.deleteAppointmentById(id);
    }

    @PutMapping("/update")
    public Appointment updateAppointment(@RequestBody Appointment appointment) throws BadRequestException {
        return appointmentService.updateAppointment(appointment);
    }

}
