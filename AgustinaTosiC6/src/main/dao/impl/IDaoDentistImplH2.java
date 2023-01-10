package main.dao.impl;

import main.dao.IDaoDentist;
import main.model.Dentist;
import org.apache.log4j.Logger;

import java.sql.*;

public class IDaoDentistImplH2 implements IDaoDentist {

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
    public void createTableDentist() {
        final String SQL_CREATE_TABLE_DENTIST = "DROP TABLE IF EXISTS DENTIST; CREATE TABLE DENTIST "
                + "("
                + " ID INT PRIMARY KEY,"
                + " SURNAME varchar(100) NOT NULL, "
                + " NAME varchar(100) NOT NULL, "
                + " MEDICALLICENSE varchar(100) NOT NULL "
                + " )";
        try {

            Statement statement = connection.createStatement();
            statement.execute(SQL_CREATE_TABLE_DENTIST);

            LOGGER.info("Dentist table has been created");

        } catch (Exception e){
            LOGGER.error("Create table Dentist Error", e);
        } finally {
            try {
                connection.close();
            } catch (Exception e){
                LOGGER.error("Close Connection Error", e);
            }
        }
    }

    @Override
    public void addDentist(Dentist dentist) {
        try{
            connection = getConnection();
            connection.setAutoCommit(false);

            psInsert = connection.prepareStatement("INSERT INTO DENTIST (ID, SURNAME, NAME, MEDICALLICENSE) VALUES (?,?,?,?)");
            psInsert.setInt(1, dentist.getId());
            psInsert.setString(2, dentist.getSurname());
            psInsert.setString(3, dentist.getName());
            psInsert.setString(4, dentist.getMedicalLicense());
            psInsert.execute();
            connection.commit();

            connection.setAutoCommit(true);
        }catch (Exception e){
            LOGGER.error("Insert Dentist Error", e);
        } finally {
            try {
                connection.close();
            }catch (Exception e){
                LOGGER.error("Close Connection Error", e);
            }
        }
    }

    @Override
    public String showDentist() {
        final String SQL_SELECT_DENTIST = "SELECT * FROM DENTIST";
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_DENTIST);
            while (rs.next()) {
                LOGGER.info(rs.getInt(1) + " - " + rs.getString(2) + " - " + rs.getString(3) + " - " + rs.getString(4));
            }
        } catch (Exception e){
            LOGGER.error("Show table Dentist Error", e);
        } finally {
            try {
                connection.close();
            }catch (Exception e){
                LOGGER.error("Close Connection Error", e);
            }
        }
        return "The dentist have been listed";
    }

    @Override
    public String updateDentist(Dentist dentist) {
        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            psInsert = connection.prepareStatement("UPDATE DENTIST SET SURNAME=?, NAME=?, MEDICALLICENSE=? WHERE ID=?");
            psInsert.setString(1, dentist.getSurname());
            psInsert.setString(2, dentist.getName());
            psInsert.setString(3, dentist.getMedicalLicense());
            psInsert.setInt(4, dentist.getId());
            psInsert.execute();
            connection.commit();
            LOGGER.info("The update has been made");
            connection.setAutoCommit(true);
        }catch (Exception e){
            LOGGER.error("Update Dentist Error", e);
        } finally {
            try {
                connection.close();
            }catch (Exception e){
                LOGGER.error("Close Connection Error", e);
            }
        }
        return "The dentist has been updated";
    }

    @Override
    public String deleteDentist(Dentist dentist) {
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            psInsert = connection.prepareStatement("DELETE FROM DENTIST WHERE id=?");
            psInsert.setInt(1, dentist.getId());
            psInsert.execute();
            connection.commit();
            LOGGER.info("The dentist has been deleted");
            connection.setAutoCommit(true);
        }catch (Exception e){
            LOGGER.error("Delete Dentist Error", e);
        } finally {
            try {
                connection.close();
            }catch (Exception e){
                LOGGER.error("Close Connection Error", e);
            }
        }
        return "The dentist has been deleted";
    }
}
