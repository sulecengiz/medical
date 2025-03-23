module com.sau.medicalmanagement.medical {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires spring.boot;

    // JavaFX modüllerinin ana sınıflara erişebilmesi için bu şekilde "exports" ekleyin
    exports com.sau.medicalmanagement.medical;
    exports com.sau.medicalmanagement.medical.Controllers;

    // FXML dosyalarınızın çalışabilmesi için uygun pakete "opens" direktifi ekleyin
    opens com.sau.medicalmanagement.medical to javafx.fxml;
    opens com.sau.medicalmanagement.medical.Controllers to javafx.fxml;
}
