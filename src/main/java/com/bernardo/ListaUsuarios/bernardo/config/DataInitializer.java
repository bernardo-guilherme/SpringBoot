package com.bernardo.ListaUsuarios.bernardo.config;

import org.javalite.activejdbc.Base;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // 1. Abre a ligação no início da aplicação
        System.out.println("Ligar à base de dados SQLite...");
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");

            // 2. Garante que a tabela existe
            String sqlCreate = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "first_name VARCHAR(56), " +
                    "last_name VARCHAR(56), " +
                    "email VARCHAR(100))";
            Base.exec(sqlCreate);

            sqlCreate = "CREATE TABLE IF NOT EXISTS books (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "title VARCHAR(56), " +
                    "autor VARCHAR(56), " +
                    "genre VARCHAR(20))";
            Base.exec(sqlCreate);

            sqlCreate = "CREATE TABLE IF NOT EXISTS cars (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "brand VARCHAR(56), " +
                    "model VARCHAR(56), " +
                    "price INTEGER)";
            Base.exec(sqlCreate);

            // A PARTIR DAQUI A LIGAÇÃO ESTÁ ABERTA E A TABELA EXISTE

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Base.close();
            System.out.println("Ligação encerrada.");
        }
    }
}