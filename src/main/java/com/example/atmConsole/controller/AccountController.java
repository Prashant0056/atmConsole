package com.example.atmConsole.controller;


import com.example.atmConsole.dto.AccountDto;
import com.example.atmConsole.dto.FindCustomerDto;
import com.example.atmConsole.model.Account;
import com.example.atmConsole.model.Customer;
import com.example.atmConsole.service.AccountService;
import com.example.atmConsole.service.CustomerService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.annotations.processing.Find;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerService customerService;;


    @PutMapping("/deposit/{accNo}")
    public ResponseEntity deposit(@RequestBody AccountDto accountDto, @PathVariable Integer accNo)
    {
        accountDto.setAccNo(accNo);
        try {
            Account account = accountService.deposit(accountDto);
            return ResponseEntity.ok().body(Map.of("status", "success", "message", "successfully deposited"));
        }catch (RuntimeException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("status","not found","message","account not found"));
        }
    }


    @PutMapping("/withdraw/{accNo}")
    public ResponseEntity withdraw(@RequestBody AccountDto accountDto, @PathVariable Integer accNo)
    {
        accountDto.setAccNo(accNo);
        try
        {
            Account account = accountService.withdraw(accountDto);
            return ResponseEntity.ok().body(Map.of("status", "success", "message", "collect your money"));
        }
        catch (RuntimeException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("status","not found","message",e.getMessage()));
        }
    }

    @GetMapping("/{accNO}")
    public ResponseEntity checkBalance(@PathVariable Integer accNo)
    {

        AccountDto newAccountDto = accountService.checkBalance(accNo);
        System.out.println("Test");
        return ResponseEntity.ok().body(Map.of("account", newAccountDto.getAccNo(),"balance",newAccountDto.getAmount()));
    }
}
