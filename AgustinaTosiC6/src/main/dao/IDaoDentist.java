package main.dao;

import main.model.Dentist;

public interface IDaoDentist {

    void createTableDentist();

    void addDentist(Dentist dentist);

    String showDentist();

    String updateDentist(Dentist dentist);

    String deleteDentist(Dentist dentist);
}
