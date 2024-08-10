package com.example.pharmacy_management_system;

public class OrderDetailsSearchModel {
    Integer billing_id;
    String customer_name;
    String phone_no;
    String medicine_name;
    Integer quantity,price;

    public OrderDetailsSearchModel(Integer billing_id, String customer_name, String phone_no, String medicine_name, Integer quantity, Integer price) {
        this.billing_id = billing_id;
        this.customer_name = customer_name;
        this.phone_no = phone_no;
        this.medicine_name = medicine_name;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getBilling_id() {
        return billing_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setBilling_id(Integer billing_id) {
        this.billing_id = billing_id;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
