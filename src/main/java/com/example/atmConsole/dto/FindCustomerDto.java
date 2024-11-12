package com.example.atmConsole.dto;

import com.example.atmConsole.model.Account;
import com.example.atmConsole.model.Customer;

public class FindCustomerDto {
    private Integer id;
    private String name;
    private Integer accNo;
    private String email;

    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public FindCustomerDto(Integer id, String name, Integer accNo) {
        this.id = id;
        this.name = name;
        this.accNo = accNo;
    }

    public FindCustomerDto(Customer customer)
    {
        this.id = customer.getId();
        this.name = customer.getName();
        this.accNo = customer.getAccount().getAccNo();
        this.email = customer.getEmail();
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

    public Integer getAccNo() {
        return accNo;
    }

    public void setAccNo(Integer accNo) {
        this.accNo = accNo;
    }


    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accNo=" + accNo +
                ", email="+ email+
                '}';
    }
}
