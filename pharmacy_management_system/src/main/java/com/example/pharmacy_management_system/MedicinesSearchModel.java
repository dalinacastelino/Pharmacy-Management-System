package com.example.pharmacy_management_system;

public class MedicinesSearchModel {
    Integer medicine_id;
    String medicine_name,description;
    Integer quantity,price;
    String expiry_date;

    public MedicinesSearchModel(Integer medicine_id, String medicine_name, String description, Integer quantity, Integer price, String expiry_date) {
        this.medicine_id = medicine_id;
        this.medicine_name = medicine_name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.expiry_date = expiry_date;
    }

    public Integer getMedicine_id() {
        return medicine_id;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setMedicine_id(Integer medicine_id) {
        this.medicine_id = medicine_id;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }
}
