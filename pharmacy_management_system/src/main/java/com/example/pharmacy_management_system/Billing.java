package com.example.pharmacy_management_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Billing {
    @FXML
    private TextField customerNameTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private TextField quantityTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField medicineNameTextField;
    @FXML
    private Button closeButton;
    @FXML
    private Button generateBillButton;
    @FXML
    private Label confirmLabel;

    public void closeButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void generateBillButtonOnAction(ActionEvent event) {
        generateBill();
    }

    public void generateBill() {
        // Get input values
        String customerName = customerNameTextField.getText().trim();
        String phoneNo = phoneNumberTextField.getText().trim();
        String medicineName = medicineNameTextField.getText().trim();
        String quantityText = quantityTextField.getText().trim();
        String priceText = priceTextField.getText().trim();

        // Validate inputs
        if (customerName.isEmpty() || phoneNo.isEmpty() || medicineName.isEmpty() || quantityText.isEmpty() || priceText.isEmpty()) {
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

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        // Check available quantity in the medicines table
        String checkQuantityQuery = "SELECT quantity FROM medicines WHERE medicine_name = ?";
        String updateMedicineQuery = "UPDATE medicines SET quantity = quantity - ? WHERE medicine_name = ?";
        String insertBillQuery = "INSERT INTO billing (customer_name, phone_no, medicine_name, quantity, price) VALUES (?, ?, ?, ?, ?)";

        try {
            // Check available quantity
            int availableQuantity = 0;
            try (PreparedStatement checkStmt = connectDB.prepareStatement(checkQuantityQuery)) {
                checkStmt.setString(1, medicineName);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    availableQuantity = rs.getInt("quantity");
                }
            }

            if (availableQuantity < quantity) {
                setConfirmLabel("Insufficient stock for the requested quantity.");
                return;
            }

            // Update the quantity in the medicines table
            try (PreparedStatement updateStmt = connectDB.prepareStatement(updateMedicineQuery)) {
                updateStmt.setInt(1, quantity);
                updateStmt.setString(2, medicineName);
                updateStmt.executeUpdate();
            }

            // Insert into billing table
            try (PreparedStatement insertStmt = connectDB.prepareStatement(insertBillQuery)) {
                insertStmt.setString(1, customerName);
                insertStmt.setString(2, phoneNo);
                insertStmt.setString(3, medicineName);
                insertStmt.setInt(4, quantity);
                insertStmt.setInt(5, price);
                int rowsAffected = insertStmt.executeUpdate();
                if (rowsAffected > 0) {
                    setConfirmLabel("Bill generated successfully.");
                } else {
                    setConfirmLabel("Failed to generate bill.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            setConfirmLabel("Error generating bill: " + e.getMessage());
        }
    }

    private void setConfirmLabel(String message) {
        if (confirmLabel != null) {
            confirmLabel.setText(message);
        }
    }
}
