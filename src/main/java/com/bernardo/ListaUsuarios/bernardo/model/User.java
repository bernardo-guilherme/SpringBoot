package com.bernardo.ListaUsuarios.bernardo.model;

public class User {

    private long id;
    private String name;
    private String arroba;
    private String cpf;

    public User() {

    }

    public User(long id, String name, String cpf, String arroba) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.arroba = arroba;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getArroba() {
        return arroba;
    }

    public void setArroba(String arroba) {
        this.arroba = arroba;
    }
}
