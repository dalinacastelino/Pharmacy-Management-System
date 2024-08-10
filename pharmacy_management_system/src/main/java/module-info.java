module com.example.pharmacy_management_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens com.example.pharmacy_management_system to javafx.fxml;
    exports com.example.pharmacy_management_system;
}