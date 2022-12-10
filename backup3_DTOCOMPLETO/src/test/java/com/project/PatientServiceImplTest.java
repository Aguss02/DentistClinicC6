package com.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.dto.PatientDTO;
import com.project.exception.BadRequestException;
import com.project.exception.ResourceNotFoundException;
import com.project.model.Patient;
import com.project.repository.PatientRepository;
import com.project.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PatientServiceImplTest {

    private PatientServiceImpl patientService;
    private PatientRepository patientRepository;

    @Autowired
    public PatientServiceImplTest(PatientServiceImpl patientService, PatientRepository patientRepository) {
        this.patientService = patientService;
        this.patientRepository = patientRepository;
    }


    // To be executed before each test. We create a new patient in order to have something already on the Database
    @BeforeEach
    public void createPatients(){

        Patient patient1 = new Patient("Ramirez","Mariano","Fake city 1", 5874814, new Date());
        patientRepository.save(patient1);

        Patient patient2 = new Patient("Gomez","Hilda","Fake city 2", 96874581, new Date());
        patientRepository.save(patient2);

        Patient patient3 = new Patient("Candocia","Lidia","Fake city 3", 24563147, new Date());
        patientRepository.save(patient3);
    }

    @Test
    public void patientToDTOTest(){

        Patient patient = new Patient("Gon","Freecs","Fake city 3", 84751424, new Date());

        ObjectMapper mapper = new ObjectMapper();

        PatientDTO patientDTO = mapper.convertValue(patient, PatientDTO.class);


        Assertions.assertNotNull(patientDTO);

    }

    @Test
    public void findAllPatientsTest(){
        Set<PatientDTO> patients = patientService.getAllPatients();
        Assertions.assertFalse(patients.isEmpty());
        Assertions.assertTrue(patients.size() == 3);
    }


    @Test
    public void findPatientsByIdTest() throws ResourceNotFoundException {
        Assertions.assertNotNull(patientService.findPatientById(1L));
    }

    @Test
    public void createPatientTest() throws BadRequestException {
        Patient patient = new Patient("Test","Test","Fake city Test", 584001452, new Date());
        patientService.savePatient(patient);

        // Checks on Database if Patient Id is present, which means that it has been created
        Assertions.assertTrue(patientRepository.findById(patient.getId()).isPresent());
    }

    @Test
    public void deletePatientByIdTest() throws ResourceNotFoundException {
        // 1l is the Id from a patient that has already been created at BeforeEach anotation
        patientService.deletePatientById(1L);
        // Checks on Database if Dentist Id is Empty, which means that it has been deleted
        Assertions.assertTrue(patientRepository.findById(1L).isEmpty());
    }

    @Test
    public void deletePatientByIdExceptionTest() throws ResourceNotFoundException {
        patientService.deletePatientById(1L);
        Assertions.assertThrows(ResourceNotFoundException.class,
                ()-> patientService.findPatientById(1L));
    }

    @Test
    public void updateDentistTest() throws ResourceNotFoundException, BadRequestException {

        Patient updatedPatient = new Patient(1L,"Test","Updated","Fake city Test", 50001452, new Date());
        patientService.updatePatient(updatedPatient);

        // Check if the values for the Dentist are equal to the ones we made
        Assertions.assertTrue(Objects.equals(patientService.findPatientById(1l).getName(), "Updated") || Objects.equals(patientService.findPatientById(1l).getSurname(), "Test"));

    }
}
