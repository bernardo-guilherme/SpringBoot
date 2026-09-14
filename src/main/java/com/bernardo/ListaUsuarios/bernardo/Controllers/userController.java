package com.bernardo.ListaUsuarios.bernardo.Controllers;

import com.bernardo.ListaUsuarios.bernardo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class userController {
    List<User> listUser = new ArrayList<User>();

    @GetMapping("/index")
    public String index(ModelMap model){
        // Evita duplicar os dados toda vez que recarrega a página se a lista já tiver itens
        if (listUser.isEmpty()) {
            listUser.add(new User(1,"João",1671882,"@jao123"));
            listUser.add(new User(2, "Maria", 168200,"@ma76291"));
        }
        model.addAttribute("size", listUser.size());
        model.addAttribute("listUsers", listUser);
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
}