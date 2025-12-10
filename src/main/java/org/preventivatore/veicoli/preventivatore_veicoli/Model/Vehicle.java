package org.preventivatore.veicoli.preventivatore_veicoli.model;

import java.util.List;

import jakarta.persistence.CascadeType;
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
    private Integer id;

    @NotBlank(message = "Type of vehicle cannot be null nor empty or blank")
    private String type;

    @NotBlank(message = "Brand of vehicle cannot be null nor empty or blank")
    private String brand;

    @NotBlank(message = "Model of vehicle cannot be null nor empty or blank")
    private String model;

    @PastOrPresent
    @NotNull(message = "Year of vehicle cannot be null")
    private Integer year;

    @Positive
    @NotNull(message = "Engine capacity of vehicle cannot be null")
    private Integer engineCapacity;

    @NotBlank(message = "Fuel of vehicle cannot be null nor empty or blank")
    private String fuel;

    @Positive
    @NotNull(message = "Base price of vehicle cannot be null")
    private Float basePrice;

    //Estimate
    @OneToMany(mappedBy = "vehicle", cascade = {CascadeType.REMOVE})
    private List<Estimate> estimates;

    //Getters and Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
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

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getEngineCapacity() {
        return this.engineCapacity;
    }

    public void setEngineCapacity(Integer engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getFuel() {
        return this.fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public Float getBasePrice() {
        return this.basePrice;
    }

    public void setBasePrice(Float basePrice) {
        this.basePrice = basePrice;
    }


    public List<Estimate> getEstimates() {
        return this.estimates;
    }

    public void setEstimates(List<Estimate> estimates) {
        this.estimates = estimates;
    }
}
