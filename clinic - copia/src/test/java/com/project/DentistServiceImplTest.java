package com.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.dto.DentistDTO;
import com.project.exception.BadRequestException;
import com.project.exception.ResourceNotFoundException;
import com.project.entity.Dentist;
import com.project.repository.DentistRepository;
import com.project.service.impl.DentistServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import java.util.Objects;
import java.util.Set;


@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//After each Test Database context is reset
@SpringBootTest
public class DentistServiceImplTest {

    DentistServiceImpl dentistService;
    DentistRepository dentistRepository;

    @Autowired
    public DentistServiceImplTest(DentistServiceImpl dentistService, DentistRepository dentistRepository) {
        this.dentistService = dentistService;
        this.dentistRepository = dentistRepository;
    }

    // To be executed before each test. We create new dentist in order to have something already on the Database
    @BeforeEach
    public void createDentists(){

        Dentist dentist1 = new Dentist("Rodriguez","Lautaro", "a2c3d5");
        dentistRepository.save(dentist1);

        Dentist dentist2 = new Dentist("Martinez","Florencia", "a2c3d6");
        dentistRepository.save(dentist2);

        Dentist dentist3 = new Dentist("Gonzalez","Gonzalo", "a2c3d7");
        dentistRepository.save(dentist3);

    }

    @Test
    public void dentistToDTOTest(){

        Dentist dentist1 = new Dentist("Di Francesco","Franco", "a2c3d8");

        ObjectMapper mapper = new ObjectMapper();

        DentistDTO dentistDTO = mapper.convertValue(dentist1, DentistDTO.class);
        Assertions.assertNotNull(dentistDTO);

    }

    @Test
    public void findAllDentistTest(){
        Set<DentistDTO> dentists = dentistService.getAllDentists();
        Assertions.assertFalse(dentists.isEmpty());
        Assertions.assertEquals(3, dentists.size());
    }


    @Test
    public void findDentistByIdTest() throws ResourceNotFoundException {
        Assertions.assertNotNull(dentistService.findDentistById(1L));
    }

    @Test
    public void createDentistTest() throws BadRequestException {
        Dentist dentist = new Dentist("Test", "Test", "test1");
        dentistService.saveDentist(dentist);
        // Checks on Database if Dentist Id is present, which means that it has been created
        Assertions.assertTrue(dentistRepository.findById(dentist.getId()).isPresent());
    }

    @Test
    public void deleteDentistByIdTest() throws ResourceNotFoundException {
        // 1l is the Id from a dentist that has already been created at BeforeEach anotation
        dentistService.deleteDentistById(1L);
        // Checks on Database if Dentist Id is Empty, which means that it has been deleted
        Assertions.assertTrue(dentistRepository.findById(1L).isEmpty());
    }

    @Test
    public void deleteDentistByIdExceptionTest() throws ResourceNotFoundException {
        dentistService.deleteDentistById(1L);
        Assertions.assertThrows(ResourceNotFoundException.class,
                ()-> dentistService.findDentistById(1L));
    }

    @Test
    public void updateDentistTest() throws ResourceNotFoundException, BadRequestException {

        Dentist updatedDentist = new Dentist(1L, "Test","Updated", "a2c3d10");
        dentistService.updateDentist(updatedDentist);


        // Check if the values for the Dentist are equal to the ones we made
        Assertions.assertTrue(Objects.equals(dentistService.findDentistById(1L).getName(), "Updated") || Objects.equals(dentistService.findDentistById(1L).getSurname(), "Test"));

    }

    @Test
    public void findDentistBySurnameTest(){
        Dentist dentist = dentistService.searchDentistBySurname("Martinez");
        Assertions.assertNotNull(dentist);
        Assertions.assertEquals("Martinez", dentist.getSurname());
    }


}
