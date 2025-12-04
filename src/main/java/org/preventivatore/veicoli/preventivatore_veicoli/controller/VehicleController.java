package org.preventivatore.veicoli.preventivatore_veicoli.controller;

import java.util.List;

import org.preventivatore.veicoli.preventivatore_veicoli.model.Vehicle;
import org.preventivatore.veicoli.preventivatore_veicoli.service.VehicleService;
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
@RequestMapping("/vehicles")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public String index(Model model){
        List<Vehicle>vehicles = vehicleService.findAll();
        model.addAttribute("vehicles", vehicles);

        return "vehicles/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model){
        model.addAttribute("vehicle", vehicleService.getById(id));

        return "vehicles/show";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("create", true); // SErve per indicare che siamo nella modalità create
        model.addAttribute("vehicle", new Vehicle());

        return "vehicles/create-or-edit";
    }

    //Add successfull message
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("vehicle") Vehicle formVehicle, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            model.addAttribute("create", true);
            return "vehicles/create-or-edit";
        }

        vehicleService.create(formVehicle);

        return "redirect:/vehicles";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        model.addAttribute("vehicle", vehicleService.getById(id));

        return "vehicles/create-or-edit";
    }

    //Add successfull message
    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("vehicle") Vehicle formVehicle, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if (bindingResult.hasErrors()) {
            return "vehicles/create-or-edit";
        }

        vehicleService.update(formVehicle);

        return "redirect:/vehicles";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        Vehicle vehicle = vehicleService.getById(id);

        vehicleService.delete(vehicle);

        return "redirect:/vehicles";
    }
}
