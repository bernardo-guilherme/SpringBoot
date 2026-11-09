package com.bernardo.ListaUsuarios.bernardo.model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.javalite.activejdbc.Model;

public class User extends Model {

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        set("name", name);
    }

    public String getCpf() {
        return getString("cpf");
    }

    public void setCpf(String cpf) {
        set("cpf", cpf);
    }

    public String getArroba() {
        return getString("arroba");
    }

    public void setArroba(String arroba) {
        set("arroba", arroba);
    }
}
