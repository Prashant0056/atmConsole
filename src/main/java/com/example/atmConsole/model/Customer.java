package com.example.atmConsole.model;

import com.example.atmConsole.dto.CustomerCreateDto;
import com.example.atmConsole.dto.UpdateCustomerDto;
import com.example.atmConsole.util.AccNumberGenerator;
import jakarta.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true)
    private String email;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(mappedBy = "customer")
    private Account account;


    public Customer(String name, Address address) {
        AccNumberGenerator newAcc = new AccNumberGenerator();
        this.name = name;
        this.address = address;
    }

    public Customer() {

    }

    public Customer(CustomerCreateDto newCustomer){
        this.name = newCustomer.getName();
        this.email = newCustomer.getEmail();
        this.address = newCustomer.getAddress();
    }

    public Customer(UpdateCustomerDto update)
    {
        this.address = update.getAddress();
    }


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
