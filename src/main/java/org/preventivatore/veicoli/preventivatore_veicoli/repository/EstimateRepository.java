package org.preventivatore.veicoli.preventivatore_veicoli.repository;

import org.preventivatore.veicoli.preventivatore_veicoli.model.Estimate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstimateRepository extends JpaRepository<Estimate, Integer> {

    //Uso long (al posto di int) per il fatto che i numeri potrebbero essere più grandi a lungo andare
    long countByCustomerId(Integer customerId);
} 
