package com.example.atmConsole.dto;

import com.example.atmConsole.model.Address;
import jakarta.validation.constraints.NotNull;

public class CustomerCreateDto {

        @NotNull(message = "name is empty")
        private String name;

        @NotNull(message = "email cannot be empty")
        private String email;

        @NotNull(message = "pin is required")
        private Integer pin;

        @NotNull(message = "An address is required")
        private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
