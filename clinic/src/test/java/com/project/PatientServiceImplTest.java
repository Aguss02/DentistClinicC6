package com.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.dto.PatientDTO;
import com.project.model.Appointment;
import com.project.model.Patient;
import com.project.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PatientServiceImplTest {

    @Autowired
    public PatientServiceImplTest(PatientServiceImpl patientService){

    }

    @BeforeEach
    public void createPatients(){

    }

     @Test
    public void PatientToDTO(){

         Patient patient = new Patient(Long.valueOf(1),"Rodriguez", "Lautaro", "Fake city", 5874215, new Date(), new Appointment());

         ObjectMapper mapper = new ObjectMapper();
         PatientDTO patientDTO = mapper.convertValue(patient, PatientDTO.class);

         Assertions.assertNotNull(patientDTO);
     }
}
