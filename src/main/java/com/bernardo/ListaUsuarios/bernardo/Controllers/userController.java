package com.bernardo.ListaUsuarios.bernardo.Controllers;

import com.bernardo.ListaUsuarios.bernardo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// A conexão com o banco é aberta e fechada pelo DbConnectionInterceptor.
@Controller
@RequestMapping("/users")
public class userController {

    @GetMapping("/index")
    public String index(ModelMap model) {
        List<User> users = new ArrayList<>(User.findAll());
        model.addAttribute("users", users);
        model.addAttribute("size", User.count());
        return "users/index";
    }

    @GetMapping("/new")
    public String userNew(ModelMap model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping("/create")
    public String userCreate(@ModelAttribute User user) {
        user.saveIt();
        return "redirect:/users/index";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, ModelMap model) {
        User user = User.findById(id);
        if (user == null) {
            return "redirect:/users/index";
        }
        model.addAttribute("user", user);
        return "users/edit";
    }

    @PostMapping("/update/{id}")
    public String userUpdate(@PathVariable Long id, @ModelAttribute User userForm) {
        User user = User.findById(id);
        if (user != null) {
            user.set("name", userForm.getName(),
                    "arroba", userForm.getArroba(),
                    "cpf", userForm.getCpf());
            user.saveIt();
        }
        return "redirect:/users/index";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Long id, ModelMap model) {
        User user = User.findById(id);
        if (user == null) {
            return "redirect:/users/index";
        }
        model.addAttribute("user", user);
        return "users/show";
    }

    @PostMapping("/delete/{id}")
    public String userDelete(@PathVariable Long id) {
        User user = User.findById(id);
        if (user != null) {
            user.delete();
        }
        return "redirect:/users/index";
    }
}
