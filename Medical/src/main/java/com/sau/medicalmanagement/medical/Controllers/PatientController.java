package com.sau.medicalmanagement.medical.Controllers;
import com.sau.medicalmanagement.medical.Database.Db;
import com.sau.medicalmanagement.medical.Models.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class PatientController {
    private Db database = new Db();
    @FXML
    private Button b1;

    @FXML
    private Button b2;

    @FXML
    private Button b3;

    @FXML
    private Button b4;

    @FXML
    private Button b5;

    @FXML
    private Button b6;

    @FXML
    private TextField t0;

    @FXML
    private TextField t1;

    @FXML
    private TextField t2;

    @FXML
    private TextField t3;

    @FXML
    void fetch(ActionEvent event) {
        Patient patient = database.getPatientById(Integer.parseInt(t0.getText()));
        if (patient.getId() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Not Found");
            alert.showAndWait();

        } else {
            t1.setText(patient.getName());
            t2.setText(patient.getAddress());
            t3.setText(patient.getTelephone());
        }
    }

    @FXML
    void delete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            int id = Integer.parseInt(t0.getText());
            boolean success = database.deletePatient(id);
            Stage stage = (Stage) b4.getScene().getWindow();


        }
    }

    @FXML
    void update(ActionEvent event) {
        try {
            int id = Integer.parseInt(t0.getText());
            String name = t1.getText();
            String address = t2.getText();
            String telephone = t3.getText();
            Patient updatedPatient = new Patient(id, name, address, telephone);
            boolean success = database.updatePatient(updatedPatient);
            if (success) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Patient updated successfully!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Patient not found or update failed!");
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Invalid ID format!");
            alert.showAndWait();
        }
    }

    @FXML
    void save(ActionEvent event) {
        try {

            int patientId = Integer.parseInt(t0.getText());
            String name = t1.getText();
            String address = t2.getText();
            String telephone = t3.getText();
            Patient existingPatient = database.getPatientById((patientId));
            if (existingPatient.getId() != 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "ID already exists!");
                alert.showAndWait();
                return;
            }
            Patient newPatient = new Patient(patientId, name, address, telephone);
            boolean isSaved = database.addPatient(newPatient);
            if (isSaved) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Patient added successfully!");
                successAlert.showAndWait();
            } else {
                Alert failAlert = new Alert(Alert.AlertType.INFORMATION, "Failed to save patient.Please try again!");
                failAlert.showAndWait();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Invalid ID format. Please enter a number.");
            alert.show();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage());
            alert.showAndWait();
        }


    }
    @FXML
    void clearPatient(ActionEvent event) {
        t0.setText("");
        t1.setText("");
        t2.setText("");
        t3.setText("");

    }

    @FXML
    void close(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Close ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Stage stage = (Stage) b5.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void switchTheDoctorPage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sau/medicalmanagement/medical/doctor-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Doctor Management System");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
