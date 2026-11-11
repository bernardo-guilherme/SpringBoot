package com.bernardo.ListaUsuarios.bernardo.Controllers;

import com.bernardo.ListaUsuarios.bernardo.model.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/carros")
public class carrosController {
    List<Car> listCarro = new ArrayList<Car>();
    
    private long nextId = 1L;

    @GetMapping("/index")
    public String index(ModelMap model){
        model.addAttribute("size", listCarro.size());
        model.addAttribute("carros", listCarro);
        return "carros/index";
    }

    @GetMapping("/new")
    public String carrroNew(ModelMap model){
        model.addAttribute("carro", new Car());
        return "carros/new";
    }

    @PostMapping("/create")
    public String carrosCreate(@ModelAttribute Car carro){
        carro.setId(nextId++);
        listCarro.add(carro);
        return "redirect:/carros/index";
    }

    @GetMapping("/edit/{id}")
    public String editCarros(@PathVariable Long id, ModelMap model){
        Car carrosEdit = findById(id);
        model.addAttribute("carro", carrosEdit);
        return "carros/edit";
    }

    @PostMapping("/update/{id}")
    public String carrosUpdate(@PathVariable Long id, @ModelAttribute Car carro, ModelMap model){
        Car CarrosEdit = findById(id);
        if (CarrosEdit != null) {
            CarrosEdit.setMarca(carro.getMarca());
            CarrosEdit.setModelo(carro.getModelo());
            CarrosEdit.setPreco(carro.getPreco());
        }
        model.addAttribute("carro", CarrosEdit);
        return "redirect:/carros/index";
    }

    @GetMapping("/show/{id}")
    public String carrosShow(@PathVariable long id, ModelMap model){
        Car carrosShow = findById(id);
        model.addAttribute("carro", carrosShow);
        return "carros/show";
    }

    @PostMapping("/delete/{id}")
    public String carroDelete(@PathVariable long id, @ModelAttribute Car carro, ModelMap model){
        listCarro.removeIf(l -> l.getId() != null && l.getId() == id);
        return "redirect:/carros/index";
    }

    private Car findById(Long id) {
        return listCarro.stream()
                .filter(l -> l.getId() != null && l.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
