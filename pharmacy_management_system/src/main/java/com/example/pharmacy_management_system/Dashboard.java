package com.example.pharmacy_management_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {
    @FXML
    private Button addMedicinesButton;
    @FXML
    private ImageView pharmacyImageView;
    @FXML
    private Button billingButton;
    @FXML
    private Button closeButton;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        File pharmacyFile = new File("images/pharmacy_dashboard3.jpg");
        Image pharmacyImage = new Image(pharmacyFile.toURI().toString());
        pharmacyImageView.setImage(pharmacyImage);
    }


    public void addMedicinesButtonOnAction(ActionEvent event) {
        createAddMedicines();

    }

    public void createAddMedicines() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addmedicines.fxml"));
            Stage AddMedicinesStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 592);

            AddMedicinesStage.initStyle(StageStyle.UNDECORATED);
            AddMedicinesStage.setScene(scene);
            AddMedicinesStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    public void billingButtonOnAction() {
        createBilling();
    }

    public void createBilling() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("billing.fxml"));
            Stage BillingStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 592);

            BillingStage.initStyle(StageStyle.UNDECORATED);
            BillingStage.setScene(scene);
            BillingStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


    }

    public void updateQuantityOnAction(ActionEvent event) {
        updateQuantity();
    }

    public void updateQuantity() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("updatequantity.fxml"));
            Stage updateQuantityStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 592);

            updateQuantityStage.initStyle(StageStyle.UNDECORATED);
            updateQuantityStage.setScene(scene);
            updateQuantityStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


    }

    public void searchMedicinesOnAction(ActionEvent event) {
        searchMedicines();
    }

    public void searchMedicines() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("searchmedicines.fxml"));
            Stage searchMedicinesStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 1080, 600);

            //searchMedicinesStage.initStyle(StageStyle.UNDECORATED);
            searchMedicinesStage.setScene(scene);
            searchMedicinesStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


    }
    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        //Platform.exit();

    }

    public void orderDetailsOnAction(ActionEvent event){
        orderDetails();
    }
    public void orderDetails(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("orderdetails.fxml"));
            Stage orderDetailsStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 1080, 600);

            //searchMedicinesStage.initStyle(StageStyle.UNDECORATED);
            orderDetailsStage.setScene(scene);
            orderDetailsStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }
}
