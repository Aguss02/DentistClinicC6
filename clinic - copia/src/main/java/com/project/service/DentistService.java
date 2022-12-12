package com.project.service;

import com.project.dto.DentistDTO;
import com.project.exception.BadRequestException;
import com.project.entity.Dentist;
import com.project.exception.ResourceNotFoundException;

import java.util.Set;

public interface DentistService {
    Set<DentistDTO> getAllDentists();
    DentistDTO findDentistById(long id) throws ResourceNotFoundException;
    Dentist saveDentist(Dentist newDentist) throws BadRequestException;
    Dentist updateDentist(Dentist dentist) throws ResourceNotFoundException, BadRequestException;
    void deleteDentistById(long id) throws ResourceNotFoundException;
    Dentist searchDentistBySurname(String surname);

}
