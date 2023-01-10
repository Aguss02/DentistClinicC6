package main;

import main.model.Dentist;
import main.model.Patient;
import main.service.DentistService;
import main.service.PatientService;
import org.apache.log4j.Logger;

public class Main {

    public static void main(String[] args) {

        Dentist dentist1 = new Dentist(0, "Alvarez", "Rodrigo","AAA001");
        Dentist dentist2 = new Dentist(1, "Martinez", "Analia","AAA002");
        Dentist dentist3 = new Dentist(2, "Gonzalez", "Gonzalo","AAA003");
        DentistService dentistService = new DentistService();

        Patient patient1 = new Patient(0,"Barkly", "Bruce", "Fake city 4848", 44871548,"2022-11-17");
        Patient patient2 = new Patient(1,"Vil", "Cruella", "Dalmatas 42255", 18745148,"2022-11-15");
        Patient patient3 = new Patient(2,"Stinson", "Barney", "Playbook 2314", 22584785,"2022-11-16");
        PatientService patientService = new PatientService();

        System.out.println("---------DENTIST--------");
        //Creating Dentist table in the Database
        dentistService.createTableDentist();

        System.out.println("----Insert----");
        // Adding dentists to the Database
        dentistService.addDentist(dentist1);
        dentistService.addDentist(dentist2);
        dentistService.addDentist(dentist3);

        // Listing existing dentists
        dentistService.showDentist();

        System.out.println("----Update----");

        //Modifying dentist #1 Name
        dentist1.setName("Ronald");
        dentistService.updateDentist(dentist1);

        // Listing existing dentists to see the changes
        dentistService.showDentist();

        System.out.println("----Delete----");

        // Deleting dentist #3

        dentistService.deleteDentist(dentist3);

        // Listing existing dentists to see the changes
        dentistService.showDentist();

        System.out.println("---------PATIENT--------");

        //Creating Patient table in the Database
        patientService.createTablePatient();

        System.out.println("----Insert----");

        // Adding Patients to the Database
        patientService.insertPatient(patient1);
        patientService.insertPatient(patient2);
        patientService.insertPatient(patient3);

        // Listing existing dentists
        patientService.showPatients();

        System.out.println("----Update----");

        //Modifying dentist #1 Name
        patient1.setName("Lumine");
        patientService.updatePatient(patient1);

        // Listing existing patients to see the changes
        patientService.showPatients();

        System.out.println("----Delete----");

        // Deleting patient #3

        patientService.deletePatient(patient3);

        // Listing existing patients to see the changes
        patientService.showPatients();

    }
}
