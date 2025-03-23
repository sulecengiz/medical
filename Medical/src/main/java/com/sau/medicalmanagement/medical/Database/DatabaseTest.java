package com.sau.medicalmanagement.medical.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseTest {

    public static void main(String[] args) {
        try {
            // PostgreSQL veritabanına bağlanmayı test ediyoruz
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/medical_db", "postgres", "suleelalee");

            if (connection != null) {
                System.out.println("Veritabanı bağlantısı başarılı!");
            } else {
                System.out.println("Veritabanına bağlanılamadı.");
            }
        } catch (SQLException e) {
            System.out.println("Hata: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
