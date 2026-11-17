package com.bernardo.ListaUsuarios.bernardo.Controllers;

import com.bernardo.ListaUsuarios.bernardo.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// A conexão com o banco é aberta e fechada pelo DbConnectionInterceptor.
@Controller
@RequestMapping("/books")
public class bookController {

    @GetMapping("/index")
    public String bookIndex(ModelMap model) {
        List<Book> books = new ArrayList<>(Book.findAll());
        model.addAttribute("books", books);
        model.addAttribute("size", Book.count());
        return "books/index";
    }

    @GetMapping("/new")
    public String bookNew(ModelMap model) {
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping("/create")
    public String bookCreate(@ModelAttribute Book book) {
        book.saveIt();
        return "redirect:/books/index";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, ModelMap model) {
        Book book = Book.findById(id);
        if (book == null) {
            return "redirect:/books/index";
        }
        model.addAttribute("book", book);
        return "books/edit";
    }

    @PostMapping("/update/{id}")
    public String bookUpdate(@PathVariable Long id, @ModelAttribute Book bookForm) {
        Book book = Book.findById(id);
        if (book != null) {
            book.set("title", bookForm.getTitle(),
                    "autor", bookForm.getAutor(),
                    "genre", bookForm.getGenre());
            book.saveIt();
        }
        return "redirect:/books/index";
    }

    @GetMapping("/show/{id}")
    public String bookShow(@PathVariable Long id, ModelMap model) {
        Book book = Book.findById(id);
        if (book == null) {
            return "redirect:/books/index";
        }
        model.addAttribute("book", book);
        return "books/show";
    }

    @PostMapping("/delete/{id}")
    public String bookDelete(@PathVariable Long id) {
        Book book = Book.findById(id);
        if (book != null) {
            book.delete();
        }
        return "redirect:/books/index";
    }
}
