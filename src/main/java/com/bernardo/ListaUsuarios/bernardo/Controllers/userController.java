package com.bernardo.ListaUsuarios.bernardo.Controllers;

import com.bernardo.ListaUsuarios.bernardo.model.User;
import org.javalite.activejdbc.Base;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class userController {

    @GetMapping("/index")
    public String index(ModelMap model){
        try {
            // 1. Abre a Conexão
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");

            // 2. Busca Todos os usuários do banco
            List<User> users = new ArrayList<>(User.findAll());
            model.addAttribute("users", users);

            // 3. Conta Tudo
            model.addAttribute("size", User.count());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            // 4. Fecha a conexão para não travar o banco
            Base.close();
        }

        return "/users/index";
    }

    @GetMapping("/new")
    public String userNew(ModelMap model){
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping("/create")
    public String userCreate(@ModelAttribute User user){
        try {
            // 1. Abrir a ligação (caso não esteja a usar o Interceptor global)
            if (!Base.hasConnection()) {
                Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            }

            // 2. Guardar o objeto diretamente na base de dados
            user.saveIt();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 3. Fechar a ligação para libertar recursos
            if (Base.hasConnection()) {
                Base.close();
            }
        }

        return "redirect:/users/index";
    }

    // 1. Carrega o formulário de edição com os dados atuais do banco
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, ModelMap model){
        try {
            if (!Base.hasConnection()) {
                Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            }

            // Busca o utilizador na base de dados pelo ID
            User userEdit = User.findById(id);
            model.addAttribute("user", userEdit);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }
        return "users/edit";
    }

    // 2. Guarda as alterações feitas no formulário
    @PostMapping("/update/{id}")
    public String userUpdate(@PathVariable long id, @ModelAttribute User userForm){
        try {
            if (!Base.hasConnection()) {
                Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            }

            // Encontra o registo original na base de dados
            User userEdit = User.findById(id);
            if (userEdit != null) {
                // Atualiza os campos com o que veio do formulário
                userEdit.set("name", userForm.get("name"),
                        "arroba", userForm.get("arroba"),
                        "cpf", userForm.get("cpf"));

                // Grava as alterações no SQLite
                userEdit.saveIt();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }
        return "redirect:/users/index";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Long id, ModelMap model){
        try {
            // 1. Abrir a ligação
            if (!Base.hasConnection()) {
                Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            }

            // 2. Buscar o utilizador pelo ID na base de dados
            User userShow = User.findById(id);
            model.addAttribute("user", userShow);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 3. Fechar a ligação
            if (Base.hasConnection()) {
                Base.close();
            }
        }

        return "users/show";
    }

    @PostMapping("/delete/{id}")
    public String userDelete(@PathVariable long id){
        try {
            if (!Base.hasConnection()) {
                Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            }

            // Encontra o utilizador e apaga-o da base de dados
            User user = User.findById(id);
            if (user != null) {
                user.delete();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }

        return "redirect:/users/index";
    }
}