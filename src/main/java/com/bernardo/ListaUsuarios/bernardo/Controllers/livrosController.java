package com.bernardo.ListaUsuarios.bernardo.Controllers;

import com.bernardo.ListaUsuarios.bernardo.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/livros")
public class livrosController {
    List<Book> listLivros = new ArrayList<Book>();

    private long nextId = 1L;

    @GetMapping("/index")
    public String index(ModelMap model){
        model.addAttribute("size", listLivros.size());
        model.addAttribute("livros", listLivros);
        return "livros/index";
    }

    @GetMapping("/new")
    public String livroNew(ModelMap model){
        model.addAttribute("livro", new Book());
        return "livros/new";
    }

    @PostMapping("/create")
    public String livrosCreate(@ModelAttribute Book livro){
        livro.setId(nextId++);
        listLivros.add(livro);
        return "redirect:/livros/index";
    }

    @GetMapping("/edit/{id}")
    public String editLivros(@PathVariable Long id, ModelMap model){
        Book livrosEdit = findById(id);
        model.addAttribute("livro", livrosEdit);
        return "livros/edit";
    }

    @PostMapping("/update/{id}")
    public String livrosUpdate(@PathVariable Long id, @ModelAttribute Book livro, ModelMap model){
        Book livrosEdit = findById(id);
        if (livrosEdit != null) {
            livrosEdit.setTitulo(livro.getTitulo());
            livrosEdit.setAutor(livro.getAutor());
            livrosEdit.setGenero(livro.getGenero());
        }
        model.addAttribute("livro", livrosEdit);
        return "redirect:/livros/index";
    }

    @GetMapping("/show/{id}")
    public String livrosShow(@PathVariable long id, ModelMap model){
        Book livrosShow = findById(id);
        model.addAttribute("livro", livrosShow);
        return "livros/show";
    }

    @PostMapping("/delete/{id}")
    public String livroDelete(@PathVariable long id, @ModelAttribute Book livro, ModelMap model){
        listLivros.removeIf(l -> l.getId() != null && l.getId() == id);
        return "redirect:/livros/index";
    }

    private Book findById(Long id) {
        return listLivros.stream()
                .filter(l -> l.getId() != null && l.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}