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

public class SearchOrderDetails implements Initializable {
    @FXML
    private TableView<OrderDetailsSearchModel> orderDetailsTableView;
    @FXML
    private TableColumn<OrderDetailsSearchModel,Integer> idTableColumn;
    @FXML
    private TableColumn<OrderDetailsSearchModel,String> customerNameTableColumn;
    @FXML
    private TableColumn<OrderDetailsSearchModel,String> phoneNumberTableColumn;
    @FXML
    private TableColumn<OrderDetailsSearchModel,String> medicineNameTableColumn;
    @FXML
    private TableColumn<OrderDetailsSearchModel,Integer> quantityTableColumn;
    @FXML
    private TableColumn<OrderDetailsSearchModel,Integer> priceTableColumn;

    @FXML
    private TextField keywordTextField;

    ObservableList<OrderDetailsSearchModel> orderDetailsSearchModelObservableList = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String billingViewQuery = "select billing_id,customer_name,phone_no,medicine_name,quantity,price from billing;";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(billingViewQuery);

            while (queryOutput.next()){
                Integer queryBillingID = queryOutput.getInt("billing_id");
                String queryCustomerName = queryOutput.getString("customer_name");
                String queryPhoneNumber = queryOutput.getString("phone_no");
                String queryMedicineName = queryOutput.getString("medicine_name");
                Integer queryQuantity = queryOutput.getInt("quantity");
                Integer queryPrice = queryOutput.getInt("price");

                orderDetailsSearchModelObservableList.add(new OrderDetailsSearchModel(queryBillingID,queryCustomerName,queryPhoneNumber,queryMedicineName,queryQuantity,queryPrice));

            }

            idTableColumn.setCellValueFactory(new PropertyValueFactory<>("billing_id"));
            customerNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
            phoneNumberTableColumn.setCellValueFactory(new PropertyValueFactory<>("phone_no"));
            medicineNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("medicine_name"));
            quantityTableColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            priceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

            orderDetailsTableView.setItems(orderDetailsSearchModelObservableList);

            FilteredList<OrderDetailsSearchModel> filteredData = new FilteredList<>(orderDetailsSearchModelObservableList,b -> true);

            keywordTextField.textProperty().addListener((observable,oldValue,newValue) ->{
                filteredData.setPredicate(orderDetailsSearchModel -> {
                    if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();
                    if(orderDetailsSearchModel.getMedicine_name().toLowerCase().indexOf(searchKeyword) > -1){
                        return true;
                    } else if (orderDetailsSearchModel.getCustomer_name().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    }
                    else if (orderDetailsSearchModel.getPhone_no().indexOf(searchKeyword) > -1) {
                        return true;
                    }
                    else if (orderDetailsSearchModel.getBilling_id().toString().indexOf(searchKeyword) > -1) {
                        return true;
                    }
                    else if (orderDetailsSearchModel.getPrice().toString().indexOf(searchKeyword) > -1) {
                        return true;
                    }
                    else{
                        return false;
                    }
                });
            });

            SortedList<OrderDetailsSearchModel> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(orderDetailsTableView.comparatorProperty());

            orderDetailsTableView.setItems(sortedData);



        }
        catch (SQLException e){
            Logger.getLogger(SearchOrderDetails.class.getName()).log(Level.SEVERE,null,e);
            e.printStackTrace();
        }
    }
}
