package com.project.controller;

import com.project.dto.DentistDTO;
import com.project.exception.BadRequestException;
import com.project.model.Dentist;
import com.project.exception.ResourceNotFoundException;
import com.project.service.impl.DentistServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/dentist")
public class DentistController {

    private final DentistServiceImpl dentistService;

    public DentistController(DentistServiceImpl dentistService) {
        this.dentistService = dentistService;
    }

    @GetMapping()
    public Set<DentistDTO> getDentists(){
        return dentistService.getAllDentists();
    }

    @GetMapping("/{id}")
    public DentistDTO findDentistById(@PathVariable long id) throws ResourceNotFoundException {
        return dentistService.findDentistById(id);
    }

    @GetMapping("/search/{surname}")
    public Dentist searchDentistBySurname(@PathVariable String surname) {
        return dentistService.searchDentistBySurname(surname);
    }

    @PostMapping("/add")
    public Dentist addDentist(@RequestBody Dentist dentist) throws BadRequestException {
       return dentistService.saveDentist(dentist);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDentist(@PathVariable long id) throws ResourceNotFoundException {
        dentistService.deleteDentistById(id);
    }

    @PutMapping("/update")
    public Dentist updateDentist(@RequestBody Dentist dentist) throws ResourceNotFoundException, BadRequestException {
       return dentistService.updateDentist(dentist);
    }


}