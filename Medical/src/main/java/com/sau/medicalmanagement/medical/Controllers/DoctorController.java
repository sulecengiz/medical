package com.sau.medicalmanagement.medical.Controllers;

import com.sau.medicalmanagement.medical.Database.Db;
import com.sau.medicalmanagement.medical.Models.Doctor;
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

public class DoctorController {
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
    void close(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Close ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Stage stage = (Stage) b5.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void save(ActionEvent event) {
        try {
            int doctorId = Integer.parseInt(t0.getText());
            String name = t1.getText();
            String clinique = t2.getText();
            Doctor existingDoctor = database.getDoctorById((doctorId));
            if (existingDoctor.getId() != 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "ID already exists!");
                alert.showAndWait();
                return;
            }
            Doctor newDoctor = new Doctor(doctorId, name, clinique);
            boolean isSaved = database.addDoctor(newDoctor);
            if (isSaved) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Doctor added successfully!");
                successAlert.showAndWait();
            } else {
                Alert failAlert = new Alert(Alert.AlertType.INFORMATION, "Failed to save doctor.Please try again!");
                failAlert.showAndWait();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Invalid ID format. Please enter a number.");
            alert.show();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void delete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            int id = Integer.parseInt(t0.getText());
            boolean success = database.deleteDoctor(id);
            Stage stage = (Stage) b4.getScene().getWindow();


        }
    }

    @FXML
    void fetch(ActionEvent event) {
        Doctor doctor = database.getDoctorById(Integer.parseInt(t0.getText()));
        if (doctor.getId() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Not Found");
            alert.showAndWait();

        } else {
            t1.setText(doctor.getName());
            t2.setText(doctor.getClinique());
        }

    }

    @FXML
    void update(ActionEvent event) {
        try {
            int id = Integer.parseInt(t0.getText());
            String name = t1.getText();
            String clinique = t2.getText();
            Doctor updatedDoctor = new Doctor(id, name, clinique);
            boolean success = database.updateDoctor(updatedDoctor);
            if (success) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Doctor updated successfully!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Doctor not found or update failed!");
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Invalid ID format!");
            alert.showAndWait();
        }

    }
    @FXML
    void clearDoctor(ActionEvent event) {
        t0.setText("");
        t1.setText("");
        t2.setText("");
    }

    @FXML
    void switchThePatientPage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sau/medicalmanagement/medical/patient-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Patient Management System");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
