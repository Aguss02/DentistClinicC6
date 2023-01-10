package main.test;

import main.model.Dentist;
import main.service.DentistService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DentistServiceTest {

        @Test
        void checkShowDentist(){
              // Dentist are previously added to the database
                DentistService dentistService = new DentistService();

                String expectedResponse = "The dentist have been listed";

                String response = dentistService.showDentist();

                Assertions.assertEquals(expectedResponse, response);

        }

        @Test
        void checkDeleteExistingDentist(){

                Dentist dentist = new Dentist(1, "Gonzalez", "Gonzalo","AAA001");
                DentistService dentistService = new DentistService();

                String expectedResponse = "The dentist has been deleted";

                String response = dentistService.deleteDentist(dentist);

                Assertions.assertEquals(expectedResponse, response);

        }

        @Test
        void checkUpdateExistingDentist(){
                Dentist dentist = new Dentist(1, "Gonzalez", "Gonzalo","AAA001");
                DentistService dentistService = new DentistService();

                String expectedResponse = "The dentist has been updated";

                String response = dentistService.updateDentist(dentist);

                Assertions.assertEquals(expectedResponse, response);
        }
}
