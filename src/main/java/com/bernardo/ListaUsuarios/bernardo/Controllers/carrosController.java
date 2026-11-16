package com.bernardo.ListaUsuarios.bernardo.Controllers;

import com.bernardo.ListaUsuarios.bernardo.model.Car;
import com.bernardo.ListaUsuarios.bernardo.model.User;
import org.javalite.activejdbc.Base;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/carros")
public class carrosController {
    @GetMapping("/index")
    public String carIndex(ModelMap model){
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");

            List<Car> cars = new ArrayList<>(Car.findAll());
            model.addAttribute("cars", cars);

            model.addAttribute("size", Car.count());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }

        return "carros/index";
    }

    @GetMapping("/new")
    public String carNew(ModelMap model){
        model.addAttribute("car", new Car());
        return "carros/new";
    }

    @PostMapping("/create")
    public String carCreate(@ModelAttribute Car car){
        try {
            if (!Base.hasConnection()) {
                Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            }

            car.saveIt();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }

        return "redirect:/carros/index";
    }

    @GetMapping("/edit/{id}")
    public String editCar(@PathVariable Long id, ModelMap model){
        try {
            if (!Base.hasConnection()) {
                Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            }

            Car carEdit = Car.findById(id);
            model.addAttribute("car", carEdit);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }
        return "carros/edit";
    }

    @PostMapping("/update/{id}")
    public String carUpdate(@PathVariable long id, @ModelAttribute Car carForm){
        try {
            if (!Base.hasConnection()) {
                Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            }

            Car carEdit = Car.findById(id);
            if (carEdit != null) {
                carEdit.set("brand", carForm.get("brand"),
                        "model", carForm.get("model"),
                        "price", carForm.get("price"));

                carEdit.saveIt();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }
        return "redirect:/carros/index";
    }

    @GetMapping("/show/{id}")
    public String carShow(@PathVariable Long id, ModelMap model){
        try {
            if (!Base.hasConnection()) {
                Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            }

            Car carShow = Car.findById(id);
            model.addAttribute("car", carShow);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }

        return "carros/show";
    }

    @PostMapping("/delete/{id}")
    public String carDelete(@PathVariable long id){
        try {
            if (!Base.hasConnection()) {
                Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            }

            Car car = Car.findById(id);
            if (car != null) {
                car.delete();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Base.hasConnection()) {
                Base.close();
            }
        }

        return "redirect:/carros/index";
    }
}
