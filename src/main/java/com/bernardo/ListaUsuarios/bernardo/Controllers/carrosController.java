package com.bernardo.ListaUsuarios.bernardo.Controllers;

import com.bernardo.ListaUsuarios.bernardo.model.Carros;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/carros")
public class carrosController {
    List<Carros> listCarros = new ArrayList<Carros>();

    // CORREÇÃO: Contador sequencial para garantir IDs únicos
    private long nextId = 1L;

    @GetMapping("/index")
    public String index(ModelMap model){
        model.addAttribute("size", listCarros.size());
        model.addAttribute("carros", listCarros);
        return "carros/index";
    }

    @GetMapping("/new")
    public String carrroNew(ModelMap model){
        model.addAttribute("carro", new Carros());
        return "carros/new";
    }

    @PostMapping("/create")
    public String carrosCreate(@ModelAttribute Carros carro){
        // CORREÇÃO: Atribui o próximo ID único
        carro.setId(nextId++);
        listCarros.add(carro);
        return "redirect:/carros/index";
    }

    @GetMapping("/edit/{id}")
    public String editCarros(@PathVariable Long id, ModelMap model){
        Carros carrosEdit = findById(id);
        model.addAttribute("carro", carrosEdit);
        return "carros/edit";
    }

    @PostMapping("/update/{id}")
    public String carrosUpdate(@PathVariable Long id, @ModelAttribute Carros carro, ModelMap model){
        Carros CarrosEdit = findById(id);
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
        Carros carrosShow = findById(id);
        model.addAttribute("carro", carrosShow);
        return "carros/show";
    }

    @PostMapping("/delete/{id}")
    public String carroDelete(@PathVariable long id, @ModelAttribute Carros carro, ModelMap model){
        listCarros.removeIf(l -> l.getId() != null && l.getId() == id);
        return "redirect:/carros/index";
    }

    private Carros findById(Long id) {
        return listCarros.stream()
                .filter(l -> l.getId() != null && l.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
