package com.bernardo.ListaUsuarios.bernardo.model;

import org.javalite.activejdbc.Model;

public class Book extends Model {
    public String getTitle() {
        return getString("title");
    }

    public void setTitle(String title) {
        set("title", title);
    }

    public String getAutor() {
        return getString("autor");
    }

    public void setAutor(String autor) {
        set("autor", autor);
    }

    public String getGenre() {
        return getString("genre");
    }

    public void setGenre(String genre) {
        set("genre", genre);
    }
}
