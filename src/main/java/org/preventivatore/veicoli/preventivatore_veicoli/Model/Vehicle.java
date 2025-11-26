package org.preventivatore.veicoli.preventivatore_veicoli.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name="vehicles")

public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Type of vehicle cannot be null nor empty or blank")
    private String type;

    @NotBlank(message = "Brand of vehicle cannot be null nor empty or blank")
    private String brand;

    @NotBlank(message = "Model of vehicle cannot be null nor empty or blank")
    private String model;

    @PastOrPresent
    @NotNull(message = "Year of vehicle cannot be null")
    private int year;

    @Positive
    @NotNull(message = "Engine capacity of vehicle cannot be null")
    private int engineCapacity;

    @NotBlank(message = "Fuel of vehicle cannot be null nor empty or blank")
    private String fuel;

    @Positive
    @NotNull(message = "Base price of vehicle cannot be null")
    private float basePrice;

    //Estimate
    @OneToMany(mappedBy = "vehicle")
    private List<Estimate> estimates;

    //Getters and Setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getEngineCapacity() {
        return this.engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getFuel() {
        return this.fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public float getBasePrice() {
        return this.basePrice;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }


    public List<Estimate> getEstimates() {
        return this.estimates;
    }

    public void setEstimates(List<Estimate> estimates) {
        this.estimates = estimates;
    }
}
