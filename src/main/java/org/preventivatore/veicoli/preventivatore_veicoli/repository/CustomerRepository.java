package org.preventivatore.veicoli.preventivatore_veicoli.repository;

import org.preventivatore.veicoli.preventivatore_veicoli.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    
}
