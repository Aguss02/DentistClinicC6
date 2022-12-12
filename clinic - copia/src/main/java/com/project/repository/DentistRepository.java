package com.project.repository;

import com.project.entity.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DentistRepository extends JpaRepository<Dentist, Long> {

    @Query("SELECT d FROM Dentist d WHERE d.surname = ?1")
    Optional<Dentist> searchDentistBySurname(String surname);

}
