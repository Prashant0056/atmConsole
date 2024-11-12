package com.example.atmConsole.dto;

import com.example.atmConsole.model.Address;

public class UpdateCustomerDto {
    private Integer pin;
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }
}
