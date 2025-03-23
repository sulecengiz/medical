package com.sau.medicalmanagement.medical.Database;

import java.sql.*;
import com.sau.medicalmanagement.medical.Models.Doctor;
import com.sau.medicalmanagement.medical.Models.Patient;

public class Db {
    private static final String URL = "jdbc:postgresql://localhost:5432/medical_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "suleelalee";
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public Db() {
        try {
            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Doctor getDoctorById(int id) {
        Doctor doctor = new Doctor();
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM doctors where id=" + id + ";");
            while (resultSet.next()) {
                doctor.setId(resultSet.getInt("id"));
                doctor.setName(resultSet.getString("name"));
                doctor.setClinique(resultSet.getString(("clinique")));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return doctor;

    }
    public Patient getPatientById(int id) {
        Patient patient = new Patient();
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM patients WHERE id=" + id + ";");
            while (resultSet.next()) {
                patient.setId(resultSet.getInt("id"));
                patient.setName(resultSet.getString("name"));
                patient.setAddress(resultSet.getString(("address")));
                patient.setTelephone(resultSet.getString("telephone"));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return patient;

    }

    public boolean addDoctor(Doctor newDoctor) throws SQLException {
        String sql = "INSERT INTO doctors (name, clinique) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, newDoctor.getName());
            preparedStatement.setString(2, newDoctor.getClinique());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }
    public boolean addPatient(Patient newPatient) throws SQLException {
        String sql = "INSERT INTO patients (name, address , telephone) VALUES (? , ? , ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, newPatient.getName());
            preparedStatement.setString(2, newPatient.getAddress());
            preparedStatement.setString(3, newPatient.getTelephone());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }

    public boolean updateDoctor(Doctor updatedDoctor) {
        String sql = "UPDATE doctors SET name = ?, clinique = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, updatedDoctor.getName());
            statement.setString(2, updatedDoctor.getClinique());
            statement.setInt(3, updatedDoctor.getId());
            int affectedRows = statement.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }

    }
    public boolean updatePatient(Patient updatedPatient) {
        String sql = "UPDATE patients SET name = ?, address = ?, telephone = ?  WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, updatedPatient.getName());
            statement.setString(2, updatedPatient.getAddress());
            statement.setString(3, updatedPatient.getTelephone());
            statement.setInt(4, updatedPatient.getId());
            int affectedRows = statement.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }

    }


    public boolean deleteDoctor(int id) {
        String sql = "DELETE FROM doctors WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }
    public boolean deletePatient(int id) {
        String sql = "DELETE FROM patients WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }
}
