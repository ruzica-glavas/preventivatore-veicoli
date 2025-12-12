package org.preventivatore.veicoli.preventivatore_veicoli.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<Estimate> findAll(){
        return estimateRepository.findAll();
    }

    public Estimate getById(Integer id){
        Optional<Estimate> estimate = estimateRepository.findById(id);
        if (estimate.isEmpty()) {
            throw new IllegalArgumentException("Estimate not found with id: " + id);
        }
        return estimate.get();
    }

    public Optional<Estimate> findById(Integer id){
        return estimateRepository.findById(id);
    }

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
    
    public Estimate update(Estimate estimate) {
    //Prendiamo dal DB gli oggetti che ci servono con un determinato Id
    Estimate existing = estimateRepository.findById(estimate.getId())
        .orElseThrow(() -> new IllegalArgumentException("Estimate not found with id: " + estimate.getId()));

    Vehicle vehicle = vehicleRepository.findById(estimate.getVehicle().getId())
        .orElseThrow(() -> new IllegalArgumentException("Vehicle not found"));

    Customer customer = customerRepository.findById(estimate.getCustomer().getId())
        .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

    //L'utente potrebbe aver tolto tutte le spunte per degli optionals, quindi la lista arriva come null. La trasformo in una lista vuota per evitare problemi successivi.
    List<VehicleOptional> selectedOptionals = estimate.getOptionals();
    if (selectedOptionals == null) {
        selectedOptionals = new ArrayList<>();
    }

    //Per ogni optional che l'utente ha seleizonato, lo ricarichiamo del DB
    List<VehicleOptional> validatedOptionals = new ArrayList<>();
    for (VehicleOptional opt : selectedOptionals) {
        VehicleOptional loaded = vehicleOptionalRepository.findById(opt.getId())
            .orElseThrow(() -> new IllegalArgumentException("Optional not found with id: " + opt.getId()));
        validatedOptionals.add(loaded);
    }

    //Ricalcolo del prezzo finale
    float finalPrice = calculateFinalPrice(vehicle, customer, validatedOptionals);

    //Aggiornameto dei campi che possono cambiare
    existing.setVehicle(vehicle);
    existing.setCustomer(customer);
    existing.setOptionals(validatedOptionals);
    existing.setNotes(estimate.getNotes());
    existing.setFinalPrice(finalPrice);

    //Salvo tutto nel DB
    return estimateRepository.save(existing);
    }

    public void delete(Estimate estimate){
        estimateRepository.delete(estimate);
    }

    public void deleteById(Integer id){
        estimateRepository.deleteById(id);
    }
}
