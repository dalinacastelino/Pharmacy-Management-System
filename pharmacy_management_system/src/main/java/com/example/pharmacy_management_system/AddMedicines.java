package com.example.pharmacy_management_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddMedicines {
    @FXML
    private TextField medicinenameTextField;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private TextField quantityTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField expirydateTextField;
    @FXML
    private Button closeButton;
    @FXML
    private Button addButton;
    @FXML
    private Label confirmLabel;

    public void closeButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void addButtonOnAction(ActionEvent event) {
        addMedicine();
    }

    public void addMedicine() {
        // Get text from fields
        String medicineName = medicinenameTextField.getText().trim();
        String description = descriptionTextField.getText().trim();
        String quantityText = quantityTextField.getText().trim();
        String priceText = priceTextField.getText().trim();
        String expiryDate = expirydateTextField.getText().trim();

        // Validate inputs
        if (medicineName.isEmpty() || description.isEmpty() || quantityText.isEmpty() || priceText.isEmpty() || expiryDate.isEmpty()) {
            setConfirmLabel("All fields must be filled in.");
            return;
        }

        int quantity;
        int price;
        try {
            quantity = Integer.parseInt(quantityText);
            price = Integer.parseInt(priceText);
        } catch (NumberFormatException e) {
            setConfirmLabel("Quantity and price must be valid numbers.");
            return;
        }

        // Database connection and insertion
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "INSERT INTO medicines (medicine_name, description, quantity, price, expiry_date) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connectDB.prepareStatement(query)) {
            pstmt.setString(1, medicineName);
            pstmt.setString(2, description);
            pstmt.setInt(3, quantity);
            pstmt.setInt(4, price);
            pstmt.setString(5, expiryDate);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                setConfirmLabel("Added medicine successfully.");
            } else {
                setConfirmLabel("Failed to add medicine.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            setConfirmLabel("Error adding medicine: " + e.getMessage());
        }
    }

    private void setConfirmLabel(String message) {
        if (confirmLabel != null) {
            confirmLabel.setText(message);
        }
    }
}
