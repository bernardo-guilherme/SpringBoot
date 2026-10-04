package com.bernardo.ListaUsuarios.bernardo.Controllers;

import com.bernardo.ListaUsuarios.bernardo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class userController {
    List<User> listUser = new ArrayList<User>();

    // CORREÇÃO: Contador sequencial de ID para evitar duplicatas após deleções
    private long nextId = 1L;

    @GetMapping("/index")
    public String index(ModelMap model){
        model.addAttribute("size", listUser.size());
        model.addAttribute("users", listUser);
        return "users/index";
    }

    @GetMapping("/new")
    public String userNew(ModelMap model){
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping("/create")
    public String userCreate(@ModelAttribute User user){
        // CORREÇÃO: Atribui o próximo ID único e incrementa o contador
        user.setId(nextId++);
        listUser.add(user);
        return "redirect:/users/index";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, ModelMap model){
        User userEdit = findById(id);
        model.addAttribute("user", userEdit);
        return "users/edit";
    }

    @PostMapping("/update/{id}")
    public String userUpdate(@PathVariable long id, @ModelAttribute User user, ModelMap model){
        User userEdit = findById(id);
        if (userEdit != null) {
            userEdit.setName(user.getName());
            userEdit.setArroba(user.getArroba());
            userEdit.setCpf(user.getCpf());
        }
        model.addAttribute("user", userEdit);
        return "redirect:/users/index";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Long id, ModelMap model){
        User userShow = findById(id);
        model.addAttribute("user", userShow);
        return "users/show";
    }

    @PostMapping("/delete/{id}")
    public String userDelete(@PathVariable long id, @ModelAttribute User user, ModelMap model){
        listUser.removeIf(u -> u.getId() == id);
        return "redirect:/users/index";
    }

    private User findById(long id) {
        return listUser.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
    }
}