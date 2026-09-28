package com.bernardo.ListaUsuarios.bernardo.Controllers;

import com.bernardo.ListaUsuarios.bernardo.model.User;
import org.springframework.boot.Banner;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class userController {
    List<User> listUser = new ArrayList<User>();

    @GetMapping("/index")
    public String index(ModelMap model){
        // Evita duplicar os dados toda vez que recarrega a página se a lista já tiver itens
        model.addAttribute("size", listUser.size());
        model.addAttribute("users", listUser);
        return "users/index"; // Removida a barra inicial para seguir o padrão do Thymeleaf (opcional, mas recomendado)
    }

    @GetMapping("/new") // Adicionada a barra aqui
    public String userNew(ModelMap model){
        model.addAttribute("user", new User());
        return "users/new"; // Aponta para templates/users/new.html
    }

    @PostMapping("/create") // Adicionada a barra aqui
    public String userCreate(@ModelAttribute User user){
        long id = listUser.size() + 1;
        user.setId(id);

        // Descomentado para salvar o usuário na lista real!
        listUser.add(user);

        return "redirect:/users/index";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, ModelMap model){
        int idInt = Math.toIntExact(id-1L);
        User userEdit = listUser.get(idInt);
        model.addAttribute("user", userEdit);
        return "/users/edit";
    }

    @PostMapping("/update/{id}")
    public String userUpdate(@PathVariable long id, @ModelAttribute User user, ModelMap model){
        int idInt = Math.toIntExact(id-1L);
        User userEdit = listUser.get(idInt);
        userEdit.setName(user.getName());
        userEdit.setArroba(user.getArroba());
        userEdit.setCpf(user.getCpf());
        model.addAttribute("user", userEdit);
        return "redirect:/users/index";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Long id, ModelMap model){
        int intId = Math.toIntExact((id-1L));
        User userShow = listUser.get(intId);
        model.addAttribute("user",userShow);
        return "/users/show";
    }

    @PostMapping("/delete/{id}")
    public String userDelete(@PathVariable long id, @ModelAttribute User user, ModelMap model){
        int idInt = Math.toIntExact(id-1L);
        listUser.remove(idInt);
        return "redirect:/users/index";
    }
}