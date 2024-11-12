package com.example.atmConsole.service;

import com.example.atmConsole.dto.AccountDto;
import com.example.atmConsole.model.Account;
import jakarta.persistence.criteria.CriteriaBuilder;

public interface AccountService {

    AccountDto find(Integer id);
    Account deposit(AccountDto accountDto);
    Account withdraw(AccountDto accountDto);
    AccountDto checkBalance(Integer accNo);
}
