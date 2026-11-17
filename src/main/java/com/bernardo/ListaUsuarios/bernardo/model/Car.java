package com.bernardo.ListaUsuarios.bernardo.model;

import org.javalite.activejdbc.Model;

public class Car extends Model {
    public String getBrand() {
        return getString("brand");
    }

    public void setBrand(String brand) {
        set("brand", brand);
    }

    public String getModel() {
        return getString("model");
    }

    public void setModel(String model) {
        set("model", model);
    }

    public Double getPrice() {
        return getDouble("price");
    }

    public void setPrice(Double price) {
        set("price", price);
    }
}
