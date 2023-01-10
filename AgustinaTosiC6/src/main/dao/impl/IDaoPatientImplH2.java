package main.dao.impl;

import main.dao.IDaoPatient;
import main.model.Patient;
import org.apache.log4j.Logger;

import java.sql.*;

public class IDaoPatientImplH2 implements IDaoPatient {

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/h2-database-clinic2";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";
    private static final Logger LOGGER = Logger.getLogger(IDaoPatientImplH2.class);
    private Connection connection = getConnection();
    private PreparedStatement psInsert = null;


    public static Connection getConnection(){

        Connection connection = null;
        DriverManager driverManager = null;

        try {
            Class.forName(DB_JDBC_DRIVER).getDeclaredConstructor().newInstance();
            connection = driverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        } catch (Exception e){
            LOGGER.error("Connection Error", e);
        }

        return connection;
    }

    @Override
    public void createTablePatient(){

        final String SQL_CREATE_TABLE_PATIENT = "DROP TABLE IF EXISTS PATIENT; CREATE TABLE PATIENT "
                + "("
                + " ID INT PRIMARY KEY,"
                + " SURNAME varchar(100) NOT NULL, "
                + " NAME varchar(100) NOT NULL, "
                + " ADDRESS varchar(100) NOT NULL, "
                + " DNI varchar(100) NOT NULL, "
                + " REGISTRATIONDATE varchar(100) NOT NULL "
                + " )";

        try {
            Statement statement = connection.createStatement();
            statement.execute(SQL_CREATE_TABLE_PATIENT);
            LOGGER.info("Patient table has been created");
        } catch (Exception e){
            LOGGER.error("Create table Patient Error", e);
        } finally {
            try {
                connection.close();
            } catch (Exception e){
                LOGGER.error("Close Connection Error", e);
            }
        }

    }

    @Override
    public void addPatient(Patient patient) {
        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            psInsert = connection.prepareStatement("INSERT INTO PATIENT (ID, SURNAME, NAME, ADDRESS, DNI, REGISTRATIONDATE) VALUES (?,?,?,?,?,?)");
            psInsert.setInt(1, patient.getId());
            psInsert.setString(2, patient.getSurname());
            psInsert.setString(3, patient.getName());
            psInsert.setString(4, patient.getAddress());
            psInsert.setInt(5, patient.getDni());
            psInsert.setString(6, patient.getRegistrationDate());
            psInsert.execute();
            connection.commit();

           connection.setAutoCommit(true);
        }catch (Exception e){
            LOGGER.error("Insert Patient Error", e);
        } finally {
            try {
                connection.close();
            } catch (Exception e){
                LOGGER.error("Close Connection Error", e);
            }
        }
    }

    @Override
    public void showPatients() {

        final String SQL_SELECT_PATIENT = "SELECT * FROM PATIENT";
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_PATIENT);
            while (rs.next()) {
                LOGGER.info(rs.getInt(1) + " - " + rs.getString(2) + " - " + rs.getString(3) + " - " + rs.getString(4) + " - " + rs.getInt(5) + " - " + rs.getString(6));
            }
        } catch (Exception e){
            LOGGER.error("Show table Patient Error", e);
        } finally {
            try {
                connection.close();
            }catch (Exception e){
                LOGGER.error("Close Connection Error", e);
            }
        }

    }

    @Override
    public String updatePatient(Patient patient) {
        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            psInsert = connection.prepareStatement("UPDATE PATIENT SET SURNAME=?, NAME=?, ADDRESS=?, DNI=?, REGISTRATIONDATE=? WHERE ID=?");
            psInsert.setString(1, patient.getSurname());
            psInsert.setString(2, patient.getName());
            psInsert.setString(3, patient.getAddress());
            psInsert.setInt(4, patient.getDni());
            psInsert.setString(5, patient.getRegistrationDate());
            psInsert.setInt(6,patient.getId());
            psInsert.execute();
            connection.commit();
            LOGGER.info("The update has been made");
            connection.setAutoCommit(true);
        }catch (Exception e){
            LOGGER.error("Update Patient Error", e);
        } finally {
            try {
                connection.close();
            }catch (Exception e){
                LOGGER.error("Close Connection Error", e);
            }
        }
        return "The patient has been updated";
    }

    @Override
    public String deletePatient(Patient patient) {
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            psInsert = connection.prepareStatement("DELETE FROM PATIENT WHERE id=?");
            psInsert.setInt(1, patient.getId());
            psInsert.execute();
            connection.commit();
            LOGGER.info("The patient has been deleted");
            connection.setAutoCommit(true);
        }catch (Exception e){
            LOGGER.error("Delete Patient Error", e);
        } finally {
            try {
                connection.close();
            }catch (Exception e){
                LOGGER.error("Close Connection Error", e);
            }
        }
        return "The patient has been deleted";
    }
}
