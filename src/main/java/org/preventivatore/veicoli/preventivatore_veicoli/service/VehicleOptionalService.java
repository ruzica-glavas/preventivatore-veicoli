package org.preventivatore.veicoli.preventivatore_veicoli.service;


import java.util.List;
import java.util.Optional;

import org.preventivatore.veicoli.preventivatore_veicoli.model.VehicleOptional;
import org.preventivatore.veicoli.preventivatore_veicoli.repository.VehicleOptionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleOptionalService {
    @Autowired
    VehicleOptionalRepository vehicleOptionalRepository;

    public List<VehicleOptional> findAll(){
        return vehicleOptionalRepository.findAll();
    }

    public VehicleOptional getById(Integer id){
        Optional<VehicleOptional> vehicleOptional = vehicleOptionalRepository.findById(id);
        if (vehicleOptional.isEmpty()) {
            throw new IllegalArgumentException("Optional not found with id: " +id);
        }
        return vehicleOptional.get();
    }

    public Optional<VehicleOptional> findById(Integer id){
        return vehicleOptionalRepository.findById(id);
    }

    public VehicleOptional create(VehicleOptional vehicleOptional){
        return vehicleOptionalRepository.save(vehicleOptional);
    }

    public VehicleOptional update(VehicleOptional vehicleOptional){
        return vehicleOptionalRepository.save(vehicleOptional);
    }

    public void deleteById(Integer id){
        vehicleOptionalRepository.deleteById(id);
    }
}
