package com.bernardo.ListaUsuarios.bernardo.Controllers;

import com.bernardo.ListaUsuarios.bernardo.model.Livros;
import com.bernardo.ListaUsuarios.bernardo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/livros")
public class livrosController {
    List<Livros> listLivros = new ArrayList<Livros>();
    @GetMapping("/index")
    public String index(ModelMap model){
        model.addAttribute("size", listLivros.size());
        model.addAttribute("livros", listLivros);
        return "livros/index";
    }

    @GetMapping("/new")
    public String livroNew(ModelMap model){
        model.addAttribute("livro", new Livros());
        return "livros/new";
    }

    @PostMapping("/create")
    public String livrosCreate(@ModelAttribute Livros livro){
        Long id = listLivros.size()+1L;
        livro.setId(id);

        listLivros.add(livro);

        return "redirect:/livros/index";
    }

    @GetMapping("/edit/{id}")
    public String editLivros(@PathVariable Long id, ModelMap model){
        int idInt = Math.toIntExact(id - 1L);
        Livros livrosEdit = listLivros.get(idInt);
        model.addAttribute("livro", livrosEdit);
        return "livros/edit";
    }

    @PostMapping("/update/{id}")
    public String livrosUpdate(@PathVariable Long id, @ModelAttribute Livros livro, ModelMap model){
        int idInt = Math.toIntExact(id - 1L);
        Livros livrosEdit = listLivros.get(idInt);
        livrosEdit.setId(livro.getId());
        livrosEdit.setTitulo(livro.getTitulo());
        livrosEdit.setAutor(livro.getAutor());
        livrosEdit.setGenero(livro.getGenero());
        model.addAttribute("livro", livrosEdit);
        return "redirect:/livros/index";
    }

    @GetMapping("/show/{id}")
    public String livrosShow(@PathVariable long id, ModelMap model){
        int idInt = Math.toIntExact(id - 1L);
        Livros livrosShow = listLivros.get(idInt);
        model.addAttribute("livro", livrosShow);
        return "livros/show";
    }

    @PostMapping("/delete/{id}")
    public String livroDelete(@PathVariable long id, @ModelAttribute Livros livro, ModelMap model){
        int intId = Math.toIntExact(id-1L);
        listLivros.remove(intId);
        return "redirect:/livros/index";
    }
}
