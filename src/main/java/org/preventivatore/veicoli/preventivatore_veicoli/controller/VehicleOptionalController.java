package org.preventivatore.veicoli.preventivatore_veicoli.controller;

import java.util.List;

import org.preventivatore.veicoli.preventivatore_veicoli.model.VehicleOptional;
import org.preventivatore.veicoli.preventivatore_veicoli.service.VehicleOptionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/optionals")
public class VehicleOptionalController {
    @Autowired
    private VehicleOptionalService vehicleOptionalService;
    
    @GetMapping
    public String index(Model model){
        List<VehicleOptional> optionals = vehicleOptionalService.findAll();
        model.addAttribute("optionals", optionals);

        return "optionals/index";
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") Integer id, Model model){
        model.addAttribute("optional", vehicleOptionalService.getById(id));

        return "optionals/show";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("create", true);
        model.addAttribute("optional", new VehicleOptional());

        return "optionals/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("optional") VehicleOptional formVehicleOptional, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            return "/optionals/create-or-edit";
        }

        vehicleOptionalService.create(formVehicleOptional);

        return "redirect:/optionals";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        model.addAttribute("optional", vehicleOptionalService.getById(id));

        return "optionals/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("optional") VehicleOptional formVehicleOptional, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            return "optionals/create-or-edit";
        }

        vehicleOptionalService.update(formVehicleOptional);

        return "redirect:/optionals";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        VehicleOptional vehicleOptional = vehicleOptionalService.getById(id);

        vehicleOptionalService.delete(vehicleOptional);

        return "redirect:/optionals";
    }
}
