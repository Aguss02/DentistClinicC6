package com.project;


import  com.fasterxml.jackson.databind.ObjectMapper;
import com.project.dto.AppointmentDTO;
import com.project.exception.BadRequestException;
import com.project.exception.ResourceNotFoundException;
import com.project.entity.Appointment;
import com.project.entity.Dentist;
import com.project.entity.Patient;
import com.project.repository.AppointmentRepository;
import com.project.service.impl.AppointmentServiceImpl;
import com.project.service.impl.DentistServiceImpl;
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
//After each Test Database context is reset
public class AppointmentServiceImplTest {

    AppointmentServiceImpl appointmentService;
    AppointmentRepository appointmentRepository;
    PatientServiceImpl patientService;
    DentistServiceImpl dentistService;

    @Autowired
    public AppointmentServiceImplTest(AppointmentServiceImpl appointmentService, PatientServiceImpl patientService, DentistServiceImpl dentistService, AppointmentRepository appointmentRepository) {
        this.appointmentService = appointmentService;
        this.patientService = patientService;
        this.dentistService = dentistService;
        this.appointmentRepository = appointmentRepository;
    }


    @BeforeEach
    public void createAppointments() throws BadRequestException {

        //Create Dentist and Patients before so we can add them to the Appointment's constructor

        Dentist dentist1 = new Dentist("Rodriguez","Lautaro", "a2c3d5");
        dentistService.saveDentist(dentist1);
        Dentist dentist2 = new Dentist("Martinez","Florencia", "a2c3d6");
        dentistService.saveDentist(dentist2);
        Dentist dentist3 = new Dentist("Gonzalez","Gonzalo", "a2c3d7");
        dentistService.saveDentist(dentist3);

        Patient patient1 = new Patient("Ramirez","Mariano","Fake city 1", 5874814, new Date());
        patientService.savePatient(patient1);
        Patient patient2 = new Patient("Gomez","Hilda","Fake city 2", 96874581, new Date());
        patientService.savePatient(patient2);
        Patient patient3 = new Patient("Candocia","Lidia","Fake city 3", 24563147, new Date());
        patientService.savePatient(patient3);

        Appointment appointment1 = new Appointment(patient1, dentist1, new Date());
        appointmentRepository.save(appointment1);
        Appointment appointment2 = new Appointment(patient2, dentist2, new Date());
        appointmentRepository.save(appointment2);
        Appointment appointment3 = new Appointment(patient3, dentist3, new Date());
        appointmentRepository.save(appointment3);

    }

    @Test
    public void  appointmentToDTOTest() throws BadRequestException, ResourceNotFoundException {

        Dentist dentistTest = new Dentist("NewDentist","Lautaro", "a2c3d5");
        dentistService.saveDentist(dentistTest);
        Patient patientTest = new Patient("newPatient","Mariano","Fake city 1", 5874814, new Date());
        patientService.savePatient(patientTest);
        Appointment appointment = new Appointment(patientTest, dentistTest, new Date());
        appointmentService.saveAppointment(appointment);

        ObjectMapper mapper = new ObjectMapper();

        AppointmentDTO appointmentDTO = mapper.convertValue(appointment, AppointmentDTO.class);

        Assertions.assertNotNull(appointmentDTO);

    }

    @Test
    public void getAllAppointmentsTest(){
        Set<AppointmentDTO> appointments = appointmentService.getAllAppointments();
        Assertions.assertFalse(appointments.isEmpty());
        Assertions.assertEquals(3, appointments.size());
    }

    @Test
    public void findAppointmentByIdTest() throws ResourceNotFoundException {
        Assertions.assertNotNull(appointmentService.findAppointmentById(1L));
    }

    @Test
    public void createAppointmentTest() throws BadRequestException, ResourceNotFoundException {
        Dentist dentist = new Dentist("Test", "Test", "test1");
        dentistService.saveDentist(dentist);
        Patient patient = new Patient("Test","Test","Fake city Test", 584001452, new Date());
        patientService.savePatient(patient);
        Appointment appointment = new Appointment(patient,dentist);
        appointmentService.saveAppointment(appointment);
        // Checks on Database if Appointment Id is present, which means that it has been created
        Assertions.assertTrue(appointmentRepository.findById(appointment.getId()).isPresent());
    }

    @Test
    public void deleteAppointmentByIdTest() throws ResourceNotFoundException {
        // 1l is the Id from an appointment that has already been created at BeforeEach annotation
        appointmentService.deleteAppointmentById(1L);
        // Checks on Database if Appointment Id is Empty, which means that it has been deleted
        Assertions.assertTrue(appointmentRepository.findById(1L).isEmpty());
    }

    @Test
    public void deleteAppointmentByIdExceptionTest() throws ResourceNotFoundException {
        appointmentService.deleteAppointmentById(1L);
        Assertions.assertThrows(ResourceNotFoundException.class,
                ()-> appointmentService.findAppointmentById(1L));
    }

    @Test
    public void updateAppointmentTest() throws ResourceNotFoundException, BadRequestException {
        Dentist dentistTest = new Dentist("NewDentist","Lautaro", "a2c3d5");
        dentistService.saveDentist(dentistTest);
        Patient patientTest = new Patient("newPatient","Mariano","Fake city 1", 5874814, new Date());
        patientService.savePatient(patientTest);
        Appointment updatedAppointment = new Appointment(1L,patientTest, dentistTest, new Date());
        appointmentService.updateAppointment(updatedAppointment);
        //Check if the Appointment is not null
        Assertions.assertNotNull(appointmentService.findAppointmentById(1L));
        //Check If the Dentist and Patient are the ones we updated
        Assertions.assertTrue(Objects.equals(updatedAppointment.getDentist().getSurname(), "NewDentist") || Objects.equals(updatedAppointment.getPatient().getSurname(), "NewPatient"));
    }
}
