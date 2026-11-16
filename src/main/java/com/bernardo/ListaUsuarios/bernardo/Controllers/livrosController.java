package com.bernardo.ListaUsuarios.bernardo.Controllers;

import com.bernardo.ListaUsuarios.bernardo.model.Book;
import com.bernardo.ListaUsuarios.bernardo.model.Car;
import org.javalite.activejdbc.Base;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/livros")
public class livrosController {
    @GetMapping("/index")
    public String bookIndex(ModelMap model){
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");

            List<Book> books = new ArrayList<>(Book.findAll());
            model.addAttribute("books", books);

            model.addAttribute("size", Book.count());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }

        return "livros/index";
    }

    @GetMapping("/new")
    public String bookNew(ModelMap model){
        model.addAttribute("book", new Book());
        return "livros/new";
    }

    @PostMapping("/create")
    public String bookCreate(@ModelAttribute Book book){
        try {
            if (!Base.hasConnection()) {
                Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            }

            book.saveIt();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }

        return "redirect:/livros/index";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, ModelMap model){
        try {
            if (!Base.hasConnection()) {
                Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            }

            Book bookEdit = Book.findById(id);
            model.addAttribute("book", bookEdit);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }
        return "livros/edit";
    }

    @PostMapping("/update/{id}")
    public String bookUpdate(@PathVariable long id, @ModelAttribute Book bookForm){
        try {
            if (!Base.hasConnection()) {
                Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            }

            Book bookEdit = Book.findById(id);
            if (bookEdit != null) {
                bookEdit.set("brand", bookForm.get("brand"),
                        "model", bookForm.get("model"),
                        "price", bookForm.get("price"));

                bookEdit.saveIt();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }
        return "redirect:/livros/index";
    }

    @GetMapping("/show/{id}")
    public String bookShow(@PathVariable Long id, ModelMap model){
        try {
            if (!Base.hasConnection()) {
                Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            }

            Book bookShow = Book.findById(id);
            model.addAttribute("book", bookShow);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }

        return "livros/show";
    }

    @PostMapping("/delete/{id}")
    public String bookDelete(@PathVariable long id){
        try {
            if (!Base.hasConnection()) {
                Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            }

            Book book = Car.findById(id);
            if (book != null) {
                book.delete();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }

        return "redirect:/livros/index";
    }
}