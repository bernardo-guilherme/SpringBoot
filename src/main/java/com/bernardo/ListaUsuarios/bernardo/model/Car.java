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

    public String getPrice() {
        return getString("arroba");
    }

    public void setPrice(String price) {
        set("price", price);
    }
}
