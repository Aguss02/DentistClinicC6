package com.project.controller;


import com.project.dto.AppointmentDTO;
import com.project.exception.BadRequestException;
import com.project.exception.ResourceNotFoundException;
import com.project.entity.Appointment;
import com.project.service.impl.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentServiceImpl appointmentService;


    @Autowired
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
    public ResponseEntity<?> saveAppointment(@RequestBody Appointment appointment) throws BadRequestException, ResourceNotFoundException {
        appointmentService.saveAppointment(appointment);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable long id) throws ResourceNotFoundException {
        appointmentService.deleteAppointmentById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAppointment(@RequestBody Appointment appointment) throws BadRequestException {
        appointmentService.updateAppointment(appointment);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
