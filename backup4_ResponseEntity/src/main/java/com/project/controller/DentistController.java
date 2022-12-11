package com.project.controller;

import com.project.dto.DentistDTO;
import com.project.exception.BadRequestException;
import com.project.exception.ResourceNotFoundException;
import com.project.model.Dentist;
import com.project.service.impl.DentistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/dentist")
public class DentistController {

    private final DentistServiceImpl dentistService;

    @Autowired
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
    public ResponseEntity<?> addDentist(@RequestBody Dentist dentist) throws BadRequestException {
        dentistService.saveDentist(dentist);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDentist(@PathVariable long id) throws ResourceNotFoundException {
        dentistService.deleteDentistById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateDentist(@RequestBody Dentist dentist) throws ResourceNotFoundException, BadRequestException {
        dentistService.updateDentist(dentist);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
