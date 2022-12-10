package com.project.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.dto.DentistDTO;
import com.project.exception.BadRequestException;
import com.project.model.Dentist;
import com.project.exception.ResourceNotFoundException;
import com.project.repository.DentistRepository;
import com.project.service.DentistService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DentistServiceImpl implements DentistService {

    private final DentistRepository dentistRepository;

    //Object used to map Entity to DTO
    private final ObjectMapper mapper;
    private static final Logger LOGGER = Logger.getLogger(DentistServiceImpl.class);

    @Autowired
    public DentistServiceImpl(DentistRepository dentistRepository, ObjectMapper mapper) {
        this.dentistRepository = dentistRepository;
        this.mapper = mapper;
    }

    @Override
    public Set<DentistDTO> getAllDentists() {

       List<Dentist> dentists = dentistRepository.findAll();

       Set<DentistDTO> dentistDTOS = new HashSet<>();

        for(Dentist dentist: dentists){
            dentistDTOS.add(mapper.convertValue(dentist, DentistDTO.class));
        }

       return dentistDTOS;

    }

    @Override
    public DentistDTO findDentistById(long id) throws ResourceNotFoundException {

        // It can bring Dentist or NULL
        Optional<Dentist> dentist = dentistRepository.findById(id);

        // Create container for DTO
        DentistDTO dentistDTO;

        if (dentist.isEmpty()){
            LOGGER.error("Dentist with Id " + id + " was not found");
            throw new ResourceNotFoundException("Dentist with Id " + id + " was not found");
        } else {
            dentistDTO = mapper.convertValue(dentist, DentistDTO.class);
        }

        return dentistDTO;

    }

    @Override
    public Dentist saveDentist(Dentist newDentist) throws BadRequestException {

        if (newDentist == null) {
            LOGGER.error("Dentist is null");
            throw new BadRequestException("Dentist is null");
        }

        dentistRepository.save(newDentist);
        LOGGER.info("Dentist has been created");
        return newDentist;
    }

    @Override
    public Dentist updateDentist(Dentist dentist) throws ResourceNotFoundException, BadRequestException {

        if (dentist == null){
            LOGGER.error("Dentist Is Null");
            throw new ResourceNotFoundException("Dentist was not found");
        } else if (dentist.getId() == null) {
            LOGGER.error("Dentist must contain Id in order to update it");
            throw new BadRequestException("Dentist must contain Id in order to update it");
        }

        LOGGER.info("Dentist with Id " + dentist.getId() + " has been updated");
        return dentistRepository.save(dentist);

    }

    @Override
    public void deleteDentistById(long id) throws ResourceNotFoundException {
        if (dentistRepository.findById(id).isEmpty()){
            LOGGER.error("Dentist with Id " + id + " Was Not Found");
            throw new ResourceNotFoundException("Dentist with Id " + id + " was not found");
        }
        LOGGER.info("Dentist with Id " + id + " has been deleted");
        dentistRepository.deleteById(id);
    }

}
