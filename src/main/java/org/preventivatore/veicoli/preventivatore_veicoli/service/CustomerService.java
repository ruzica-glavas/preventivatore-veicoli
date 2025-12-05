package org.preventivatore.veicoli.preventivatore_veicoli.service;

import java.util.List;
import java.util.Optional;

import org.preventivatore.veicoli.preventivatore_veicoli.model.Customer;
import org.preventivatore.veicoli.preventivatore_veicoli.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Customer getById(Integer id){
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isEmpty()){
            throw new IllegalArgumentException("Customer not found with id: " + id);
        }
        return customer.get();
    }

    public Optional<Customer> findById(Integer id){
        return customerRepository.findById(id);
    }

    public Customer create(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer update(Customer customer){
        return customerRepository.save(customer);
    }

    public void delete(Customer customer){
        customerRepository.delete(customer);
    }

    public void deleteById(Integer id){
        customerRepository.deleteById(id);
    }
}
