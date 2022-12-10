package com.project.service;

import com.project.dto.DentistDTO;
import com.project.exception.BadRequestException;
import com.project.model.Dentist;
import com.project.exception.NotFoundException;

import java.util.Set;

public interface DentistService {
    Set<DentistDTO> getAllDentists();
    DentistDTO findDentistById(long id) throws NotFoundException;
    Dentist saveDentist(Dentist newDentist) throws BadRequestException;
    Dentist updateDentist(Dentist dentist) throws NotFoundException, BadRequestException;
    void deleteDentist(long id) throws NotFoundException;


}
