package com.project.service.impl;

import com.project.model.Dentist;
import com.project.repository.DentistRepository;
import com.project.service.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DentistServiceImpl implements DentistService {
    @Autowired
    private DentistRepository dentistRepository;

    public DentistServiceImpl(DentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

    @Override
    public List<Dentist> getAllDentists() {
        return dentistRepository.findAll();
    }

    @Override
    public Optional<Dentist> findDentistById(long id) {
        return dentistRepository.findById(id);
    }

    @Override
    public Dentist addDentist(Dentist newDentist) {
        if (newDentist != null){
            dentistRepository.save(newDentist);
        }
        return newDentist;
    }

    @Override
    public String deleteDentist(long id) {
        if (dentistRepository.findById(id).isPresent()){
            dentistRepository.deleteById(id);
            return "Dentist with id " + id + " has been deleted";
        }
        return "Dentist with id " + id + " not found";
        // Armar la excepcion
    }

    @Override
    public String updateDentist(long id, Dentist dentist) {
        if (dentistRepository.findById(id).isPresent()){
            Dentist updateDentist = dentistRepository.findById(id).get();
            updateDentist.setSurname(dentist.getSurname());
            updateDentist.setName(dentist.getName());
            updateDentist.setMedicalLicense(dentist.getMedicalLicense());
            dentistRepository.save(updateDentist);
            return "Dentist with id " + id + " has been updated";
        }
        return "Dentist with id " + id + " not found";
        // Armar la excepcion
    }

}
