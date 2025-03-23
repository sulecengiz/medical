package com.sau.medicalmanagement.medical;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/sau/medicalmanagement/medical/doctor-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 500);
            stage.setScene(scene);
            stage.setTitle("Doctor Management System");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FXML dosyası yüklenemedi.");
        }
    }


    public static void main(String[] args) {
        // JavaFX başlatılır
        Application.launch(Main.class, args);

        // Spring Boot başlatılır
        SpringApplication.run(Application.class, args);
        launch();
    }
}