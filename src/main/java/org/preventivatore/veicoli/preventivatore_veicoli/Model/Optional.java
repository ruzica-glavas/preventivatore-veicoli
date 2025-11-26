package org.preventivatore.veicoli.preventivatore_veicoli.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "optionals")

public class Optional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name of optional cannot be null nor empty or blank")
    private String name;

    @Positive
    @NotNull(message = "Price of optional cannot be null")
    private Float price;

    //Estimate
    @ManyToMany(mappedBy = "optionals")
    private List<Estimate> estimates;

    //Getters and setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public List<Estimate> getEstimates() {
        return this.estimates;
    }

    public void setEstimates(List<Estimate> estimates) {
        this.estimates = estimates;
    }    
}
