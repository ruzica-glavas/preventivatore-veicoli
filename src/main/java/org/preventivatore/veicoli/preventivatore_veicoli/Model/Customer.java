package org.preventivatore.veicoli.preventivatore_veicoli.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "customers")

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "firstName cannot be null nor empty or blank")
    private String firstName;

    @NotBlank(message = "lastName cannot be null nor empty or blank")
    private String lastName;

    @NotBlank(message = "email cannot be null nor empty or blank")
    private String email;

    @NotBlank(message = "phone cannot be null nor empty or blank")
    private String phone;

    //Estimate
    @OneToMany(mappedBy = "customer")
    private List<Estimate> estimates;

    //Getters and Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Estimate> getEstimates() {
        return this.estimates;
    }

    public void setEstimates(List<Estimate> estimates) {
        this.estimates = estimates;
    }
}
