package org.preventivatore.veicoli.preventivatore_veicoli.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.preventivatore.veicoli.preventivatore_veicoli.model.Customer;
import org.preventivatore.veicoli.preventivatore_veicoli.model.Estimate;
import org.preventivatore.veicoli.preventivatore_veicoli.model.Vehicle;
import org.preventivatore.veicoli.preventivatore_veicoli.model.VehicleOptional;
import org.preventivatore.veicoli.preventivatore_veicoli.repository.CustomerRepository;
import org.preventivatore.veicoli.preventivatore_veicoli.repository.EstimateRepository;
import org.preventivatore.veicoli.preventivatore_veicoli.repository.VehicleOptionalRepository;
import org.preventivatore.veicoli.preventivatore_veicoli.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstimateService {
    @Autowired
    EstimateRepository estimateRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    VehicleOptionalRepository vehicleOptionalRepository;

    public Estimate create(Estimate estimate){
        

        //Vado a recuperare i vehicle e i customer. Non ho usato Optional per usare una scrittura alternativa a quella usata fino ad ora.
        Vehicle vehicle = vehicleRepository.findById(estimate.getVehicle().getId())
            .orElseThrow(()->new IllegalArgumentException("Vehicle not found"));

        Customer customer = customerRepository.findById(estimate.getCustomer().getId())
            .orElseThrow(()->new IllegalArgumentException("Customer not found"));


        //Lista degli optional selezionati
        List<VehicleOptional> vehicleOptionals = estimate.getOptionals();

        //Evita il crash se List é Null
        if(vehicleOptionals==null){
            vehicleOptionals=new ArrayList<>();
        }

        //Ricaricare ogni optional dal DB per vedere se esiste davvero
        List<VehicleOptional> vehicleOptionalsValidate = new ArrayList<>();
        for(VehicleOptional vehicleOptional : vehicleOptionals){
            VehicleOptional loeadedOptional = vehicleOptionalRepository.findById(vehicleOptional.getId())
                .orElseThrow(()->new IllegalArgumentException("Optional not found with id: " + vehicleOptional.getId()));
            vehicleOptionalsValidate.add(loeadedOptional);
        }

        //Calcolo prezzo finale
        float finalPrice = calculateFinalPrice(vehicle, customer, vehicleOptionals);

        //Dati finali dell'Estimate
        estimate.setVehicle(vehicle);
        estimate.setCustomer(customer);
        estimate.setOptionals(vehicleOptionalsValidate);
        estimate.setFinalPrice(finalPrice);
        estimate.setCreatedAt(LocalDate.now());

        return estimateRepository.save(estimate);
    }

        //Metodo per il calcolo per il prezzo finale
        private float calculateFinalPrice(Vehicle vehicle, Customer customer, List<VehicleOptional> vehicleOptionals) {
            float finalPrice = vehicle.getBasePrice();

            if (vehicleOptionals != null) {
                for (VehicleOptional optional : vehicleOptionals) {
                    finalPrice += optional.getPrice();
                }
            }

            //Sconto 3% con almento 3 optionls
            if(vehicleOptionals.size()>=3){
                finalPrice-=finalPrice*0.03f;
            }

            //Sconto 2% se il cliente é nuovo
            //Controllo dei customer via Repository
            long estimateCount = estimateRepository.countByCustomerId(customer.getId());

            if(estimateCount==0){
                finalPrice-=finalPrice*0.02f;
            }

            return finalPrice;
        }
}
