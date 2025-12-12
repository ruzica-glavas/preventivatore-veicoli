package org.preventivatore.veicoli.preventivatore_veicoli.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "estimates")

public class Estimate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Float finalPrice;

    private LocalDate createdAt;

    @Lob
    private String notes;

    //Vehicle
    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    //Customer
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable=false)
    private Customer customer;

    //Optional
    @ManyToMany
    @JoinTable(
        name="estimate_optional",
        joinColumns = @JoinColumn(name = "estimate_id"),
        inverseJoinColumns = @JoinColumn(name = "optional_id")
    )
    private List<VehicleOptional> optionals;

    //Getters and setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getFinalPrice() {
        return this.finalPrice;
    }

    public void setFinalPrice(Float finalPrice) {
        this.finalPrice = finalPrice;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<VehicleOptional> getOptionals() {
        return this.optionals;
    }

    public void setOptionals(List<VehicleOptional> optionals) {
        this.optionals = optionals;
    }
}
