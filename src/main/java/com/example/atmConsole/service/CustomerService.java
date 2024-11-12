package com.example.atmConsole.service;

import com.example.atmConsole.dto.CustomerCreateDto;
import com.example.atmConsole.dto.FindCustomerDto;
import com.example.atmConsole.dto.UpdateCustomerDto;
import com.example.atmConsole.model.Customer;

import java.util.ArrayList;
import java.util.List;

public interface CustomerService {

    void create(CustomerCreateDto customer);

    FindCustomerDto get(Integer id);

    List<FindCustomerDto> getAll();
    Customer update(UpdateCustomerDto customer, Integer id);

    FindCustomerDto getByAcc(Integer accNo);

    void delete(Integer id);
}
