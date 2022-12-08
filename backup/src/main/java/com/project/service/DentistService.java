package com.project.service;

import com.project.model.Dentist;

import java.util.List;
import java.util.Optional;

public interface DentistService {
    List<Dentist> getAllDentists();
    Optional<Dentist> findDentistById(long id);
    Dentist addDentist(Dentist newDentist);
    String deleteDentist(long id);
    String updateDentist(long id, Dentist dentist);

  //  Dentist findDentistByName(String name);

}
