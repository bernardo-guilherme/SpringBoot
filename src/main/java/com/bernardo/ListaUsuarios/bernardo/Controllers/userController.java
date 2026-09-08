package com.bernardo.ListaUsuarios.bernardo.Controllers;

import com.bernardo.ListaUsuarios.bernardo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class userController {
    List<User> listUser = new ArrayList<User>();

    @GetMapping("/index")
    public String index(ModelMap model){
        listUser.add(new User(1,"João",1671882,"@jao123"));
        listUser.add(new User(2, "Maria", 168200,"@ma76291"));
        model.addAttribute("size",listUser.size());
        model.addAttribute("listUsers", listUser);
        return "/users/index";
    }

    @GetMapping("new")
    public String userNew(ModelMap model){
        model.addAttribute("user", new User());
        return "/users/new";
    }
}
