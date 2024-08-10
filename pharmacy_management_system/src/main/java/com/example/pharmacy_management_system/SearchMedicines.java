package com.example.pharmacy_management_system;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchMedicines implements Initializable {
    @FXML
    private TableView<MedicinesSearchModel> medicineTableView;
    @FXML
    private TableColumn<MedicinesSearchModel,Integer> idTableColumn;
    @FXML
    private TableColumn<MedicinesSearchModel,String> medicineNameTableColumn;
    @FXML
    private TableColumn<MedicinesSearchModel,String> descriptionTableColumn;
    @FXML
    private TableColumn<MedicinesSearchModel,Integer> quantityTableColumn;
    @FXML
    private TableColumn<MedicinesSearchModel,Integer> priceTableColumn;
    @FXML
    private TableColumn<MedicinesSearchModel,String> expiryDateTableColumn;
    @FXML
    private TextField keywordTextField;

    ObservableList<MedicinesSearchModel> medicinesSearchModelObservableList = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String medicineViewQuery = "select medicine_id,medicine_name,description,quantity,price,expiry_date from medicines;";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(medicineViewQuery);

            while (queryOutput.next()){
                Integer queryMedicineID = queryOutput.getInt("medicine_id");
                String queryMedicineName = queryOutput.getString("medicine_name");
                String queryDescription = queryOutput.getString("description");
                Integer queryQuantity = queryOutput.getInt("quantity");
                Integer queryPrice = queryOutput.getInt("price");
                String queryExpiryDate= queryOutput.getString("expiry_date");

                medicinesSearchModelObservableList.add(new MedicinesSearchModel(queryMedicineID,queryMedicineName,queryDescription,queryQuantity,queryPrice,queryExpiryDate));

            }

            idTableColumn.setCellValueFactory(new PropertyValueFactory<>("medicine_id"));
            medicineNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("medicine_name"));
            descriptionTableColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            quantityTableColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            priceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            expiryDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("expiry_date"));

            medicineTableView.setItems(medicinesSearchModelObservableList);

            FilteredList<MedicinesSearchModel> filteredData = new FilteredList<>(medicinesSearchModelObservableList,b -> true);

            keywordTextField.textProperty().addListener((observable,oldValue,newValue) ->{
                filteredData.setPredicate(medicinesSearchModel -> {
                    if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();
                    if(medicinesSearchModel.getMedicine_name().toLowerCase().indexOf(searchKeyword) > -1){
                        return true;
                    } else if (medicinesSearchModel.getDescription().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    }
                    else if (medicinesSearchModel.getDescription().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    }
                    else if (medicinesSearchModel.getExpiry_date().indexOf(searchKeyword) > -1) {
                        return true;
                    }
                    else if (medicinesSearchModel.getMedicine_id().toString().indexOf(searchKeyword) > -1) {
                        return true;
                    }
                    else{
                        return false;
                    }
                });
            });

            SortedList<MedicinesSearchModel> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(medicineTableView.comparatorProperty());

            medicineTableView.setItems(sortedData);



        }
        catch (SQLException e){
            Logger.getLogger(SearchMedicines.class.getName()).log(Level.SEVERE,null,e);
            e.printStackTrace();
        }
    }
}
