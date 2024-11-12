package com.example.atmConsole.controller;

import com.example.atmConsole.dto.CustomerCreateDto;
import com.example.atmConsole.dto.ResponseDto;
import com.example.atmConsole.model.Account;
import com.example.atmConsole.repo.AccountRepo;
import com.example.atmConsole.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    TransactionService transactionService;

    @PostMapping("/validate/{accNo}")
    public ResponseEntity validateCustomer(@PathVariable Integer accNo, @RequestBody CustomerCreateDto dto){
       return transactionService.validate(accNo,dto.getPin());

    }
}
