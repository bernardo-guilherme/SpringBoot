package com.bernardo.ListaUsuarios.bernardo.Controllers;

import com.bernardo.ListaUsuarios.bernardo.model.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// A conexão com o banco é aberta e fechada pelo DbConnectionInterceptor.
@Controller
@RequestMapping("/cars")
public class carController {

    @GetMapping("/index")
    public String carIndex(ModelMap model) {
        List<Car> cars = new ArrayList<>(Car.findAll());
        model.addAttribute("cars", cars);
        model.addAttribute("size", Car.count());
        return "cars/index";
    }

    @GetMapping("/new")
    public String carNew(ModelMap model) {
        model.addAttribute("car", new Car());
        return "cars/new";
    }

    @PostMapping("/create")
    public String carCreate(@ModelAttribute Car car) {
        car.saveIt();
        return "redirect:/cars/index";
    }

    @GetMapping("/edit/{id}")
    public String editCar(@PathVariable Long id, ModelMap model) {
        Car car = Car.findById(id);
        if (car == null) {
            return "redirect:/cars/index";
        }
        model.addAttribute("car", car);
        return "cars/edit";
    }

    @PostMapping("/update/{id}")
    public String carUpdate(@PathVariable Long id, @ModelAttribute Car carForm) {
        Car car = Car.findById(id);
        if (car != null) {
            car.set("brand", carForm.getBrand(),
                    "model", carForm.getModel(),
                    "price", carForm.getPrice());
            car.saveIt();
        }
        return "redirect:/cars/index";
    }

    @GetMapping("/show/{id}")
    public String carShow(@PathVariable Long id, ModelMap model) {
        Car car = Car.findById(id);
        if (car == null) {
            return "redirect:/cars/index";
        }
        model.addAttribute("car", car);
        return "cars/show";
    }

    @PostMapping("/delete/{id}")
    public String carDelete(@PathVariable Long id) {
        Car car = Car.findById(id);
        if (car != null) {
            car.delete();
        }
        return "redirect:/cars/index";
    }
}
