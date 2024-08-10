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

public class UpdateQuantity {

    @FXML
    private TextField medicineNameTextField;
    @FXML
    private TextField quantityTextField;
    @FXML
    private Button updateButton;
    @FXML
    private Label confirmLabel;
    @FXML
    private Button closeButton;

    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        //Platform.exit();
    }

    public void updateButtonOnAction(ActionEvent event) {
        updateMedicineQuantity();
    }

    private void updateMedicineQuantity() {
        String medicineName = medicineNameTextField.getText().trim();
        String quantityText = quantityTextField.getText().trim();

        if (medicineName.isEmpty() || quantityText.isEmpty()) {
            // Handle empty input
            confirmLabel.setText("Please fill in both fields.");
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityText);
        } catch (NumberFormatException ex) {
            // Handle invalid number input
            confirmLabel.setText("Please enter a valid number.");
            return;
        }

        String query = "UPDATE medicines SET quantity = ? WHERE medicine_name = ?";

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(query);
            preparedStatement.setInt(1, quantity);
            preparedStatement.setString(2, medicineName);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                confirmLabel.setText("Quantity updated successfully.");
            } else {
                confirmLabel.setText("Medicine not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            confirmLabel.setText("Error updating quantity.");
        }
        }
    }

