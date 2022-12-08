package com.project.controller;


import com.project.model.Appointment;
import com.project.service.impl.AppointmentServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentServiceImpl appointmentService;
    private static final Logger LOGGER = Logger.getLogger(AppointmentController.class);


    public AppointmentController(AppointmentServiceImpl appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping()
    public List<Appointment> getAllAppointments(){
        return null;
    }

    @PostMapping("/add")
    public void addAppointment(@RequestBody Appointment appointment){

    }

    @DeleteMapping("/delete/{id}")
    public void deleteAppointment(@PathVariable long id) {

    }

    @PutMapping("/update/{id}")
    public void updateAppointment(@PathVariable long id, @RequestBody Appointment appointment){
    }

}
