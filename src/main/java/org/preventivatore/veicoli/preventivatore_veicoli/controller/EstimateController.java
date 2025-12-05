package org.preventivatore.veicoli.preventivatore_veicoli.controller;

import java.util.List;

import org.preventivatore.veicoli.preventivatore_veicoli.model.Estimate;
import org.preventivatore.veicoli.preventivatore_veicoli.service.CustomerService;
import org.preventivatore.veicoli.preventivatore_veicoli.service.EstimateService;
import org.preventivatore.veicoli.preventivatore_veicoli.service.VehicleOptionalService;
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
@RequestMapping("/estimates")
public class EstimateController {
    @Autowired
    private EstimateService estimateService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private VehicleOptionalService vehicleOptionalService;

    @GetMapping
    public String index(Model model){
        List<Estimate> estimates = estimateService.findAll();
        model.addAttribute("estimates", estimates);

        return "estimates/index";
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") Integer id, Model model){
        model.addAttribute("estimate", estimateService.getById(id));

        return "estimates/show";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("estimate", new Estimate());
        model.addAttribute("vehicles", vehicleService.findAll());
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("optionals", vehicleOptionalService.findAll());

        return "estimates/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("estimate") Estimate formEstimate, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        model.addAttribute("vehicles", vehicleService.findAll());
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("optionals", vehicleOptionalService.findAll());

        if (bindingResult.hasErrors()) {
            return "estimates/create-or-edit";
        }
        estimateService.create(formEstimate);

        return "redirect:/estimates";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        model.addAttribute("estimate", estimateService.getById(id));
        model.addAttribute("vehicles", vehicleService.findAll());
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("optionals", vehicleOptionalService.findAll());

        return "estimates/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("estimate") Estimate formEstimate, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        model.addAttribute("vehicles", vehicleService.findAll());
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("optionals", vehicleOptionalService.findAll());

        if (bindingResult.hasErrors()) {
            return "estimates/create-or-edit";
        }
        estimateService.update(formEstimate);

        return "redirect:/estimates";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        Estimate estimate = estimateService.getById(id);

        estimateService.delete(estimate);

        return "redirect:/estimates";
    }
    
}
