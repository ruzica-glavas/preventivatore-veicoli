package org.preventivatore.veicoli.preventivatore_veicoli.controller;

import java.util.List;

import org.preventivatore.veicoli.preventivatore_veicoli.model.Customer;
import org.preventivatore.veicoli.preventivatore_veicoli.service.CustomerService;
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
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String index(Model model){
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);

        return "customers/index";
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") Integer id, Model model){
        model.addAttribute("customer", customerService.getById(id));

        return "customers/show";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("create", true);
        model.addAttribute("customer", new Customer());

        return "customers/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("customer") Customer formCustomer, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            return "customers/create-or-edit";
        }

        customerService.create(formCustomer);

        return "redirect:/customers";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        model.addAttribute("customer", customerService.getById(id));
        model.addAttribute("edit", true);

        return "customers/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("customer") Customer formCustomer, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            return "customers/create-or-edit";
        }

        customerService.update(formCustomer);

        return "redirect:/customers";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        Customer customer = customerService.getById(id);

        customerService.delete(customer);

        return "redirect:/customers";
    }
}
