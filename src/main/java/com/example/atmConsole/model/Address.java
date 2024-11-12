package com.example.atmConsole.model;


import com.example.atmConsole.dto.CustomerCreateDto;
import com.example.atmConsole.dto.UpdateCustomerDto;
import jakarta.persistence.*;
import org.apache.catalina.User;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String district;
    private String municipality;
    private String street;

    @OneToOne(mappedBy = "address")
    private Customer customer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Address() {
    }

    public Address(CustomerCreateDto customer)
    {
        this.district = customer.getAddress().getDistrict();
        this.municipality = customer.getAddress().getMunicipality();
        this.street = customer.getAddress().getStreet();
    }


}
