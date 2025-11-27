package org.preventivatore.veicoli.preventivatore_veicoli.service;

import java.util.List;
import java.util.Optional;

import org.preventivatore.veicoli.preventivatore_veicoli.model.Vehicle;
import org.preventivatore.veicoli.preventivatore_veicoli.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;

    public List<Vehicle> findAll(){
        return vehicleRepository.findAll();
    }

    public Vehicle getById(Integer id){
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if(vehicle.isEmpty()){
            throw new IllegalArgumentException("Vehicle not found with id: " + id);
        }
        return vehicle.get();
    }

    public Optional<Vehicle> findById(Integer id){
        return vehicleRepository.findById(id);
    }

    public Vehicle create(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }

    public Vehicle update(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }

    public void deleteById(Integer id){
        vehicleRepository.deleteById(id);
    }
}
