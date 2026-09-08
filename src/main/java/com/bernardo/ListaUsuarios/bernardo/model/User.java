package com.bernardo.ListaUsuarios.bernardo.model;

public class User {

    private long id;
    private String name;
    private String arroba;
    private int cpf;

    public User() {

    }

    public long getId() {
        return id;
    }

    public User(long id, String name, int cpf, String arroba) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.arroba = arroba;
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

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getArroba() {
        return arroba;
    }

    public void setArroba(String arroba) {
        this.arroba = arroba;
    }
}
