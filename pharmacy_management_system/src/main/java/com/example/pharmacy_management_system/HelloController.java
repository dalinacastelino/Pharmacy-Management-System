package com.example.pharmacy_management_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;

    public void loginButtonOnAction(ActionEvent e){

        if(usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false){
            validateLogin();
        }
        else{
            loginMessageLabel.setText("Please enter username and password");
        }
    }


    public void cancelButtonOnAction(ActionEvent e){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "select count(1) from login where username='" + usernameTextField.getText() + "' and password = '" + passwordPasswordField.getText()+ "'";

        try {
            Statement statement =  connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    //loginMessageLabel.setText("Login Successful");
                    createDashboard();

                }
                else{
                    loginMessageLabel.setText("Invalid Login. Please Try Again");
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void signUpButtonOnAction(ActionEvent event){
        createAccountForm();
    }

    public void createAccountForm(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register.fxml"));
            Stage registerStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 520, 569);

            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(scene);
            registerStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void createDashboard(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dashboard.fxml"));
            Stage DashboardStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 718, 500);

            DashboardStage.initStyle(StageStyle.UNDECORATED);
            DashboardStage.setScene(scene);
            DashboardStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    }




