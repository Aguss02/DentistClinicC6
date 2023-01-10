package main.service;


import main.dao.IDaoDentist;
import main.dao.impl.IDaoDentistImplH2;
import main.model.Dentist;

public class DentistService {
    IDaoDentist dentistDao;

    public DentistService() {
        this.dentistDao = new IDaoDentistImplH2();
    }

    public void createTableDentist(){
        dentistDao.createTableDentist();
    }

    public void addDentist(Dentist dentist){
        dentistDao.addDentist(dentist);
    }

    public String showDentist(){
        return dentistDao.showDentist();
    }

    public String updateDentist(Dentist dentist){
        return dentistDao.updateDentist(dentist);
    }

    public String deleteDentist(Dentist dentist){return dentistDao.deleteDentist(dentist); }
}
