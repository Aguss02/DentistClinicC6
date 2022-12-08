package com.project.controller;

import com.project.dto.DentistDTO;
import com.project.exception.BadRequestException;
import com.project.model.Dentist;
import com.project.exception.NotFoundException;
import com.project.service.impl.DentistServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/dentist")
public class DentistController {

    private final DentistServiceImpl dentistService;
    private static final Logger LOGGER = Logger.getLogger(PatientController.class);

    public DentistController(DentistServiceImpl dentistService) {
        this.dentistService = dentistService;
    }

    @GetMapping()
    public Set<DentistDTO> getDentists(){
        return dentistService.getAllDentists();
    }

    @GetMapping("/{id}")
    public DentistDTO findDentistById(@PathVariable long id) throws NotFoundException {
        return dentistService.findDentistById(id);
    }

    @PostMapping("/add")
    public Dentist addDentist(@RequestBody Dentist dentist) throws BadRequestException {
       return dentistService.saveDentist(dentist);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDentist(@PathVariable long id) throws NotFoundException {
        dentistService.deleteDentist(id);
    }

    @PutMapping("/update")
    public Dentist updateDentist(@RequestBody Dentist dentist) throws NotFoundException, BadRequestException {
       return dentistService.updateDentist(dentist);
    }


}
