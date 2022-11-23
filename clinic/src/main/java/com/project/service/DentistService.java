package com.project.service;

import com.project.model.Dentist;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface DentistService {

    public List<Dentist> getAllDentists();
    public Optional<Dentist> findDentistById(long id);
    public Dentist addDentist(Dentist newDentist);
    public String deleteDentist(long id);
    public String updateDentist(long id, Dentist dentist);

}
