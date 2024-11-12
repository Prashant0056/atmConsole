package com.example.atmConsole.service;

import com.example.atmConsole.dto.AccountDto;
import com.example.atmConsole.model.Account;
import com.example.atmConsole.model.Transaction;
import com.example.atmConsole.repo.AccountRepo;
import com.example.atmConsole.repo.TransactionRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private TransactionRepo transactionRepo;
    @Override
    @Transactional
    public Account deposit(AccountDto accountDto) {
        Optional<Account> optionalAccount = accountRepo.findByAccNo(accountDto.getAccNo());
        if(optionalAccount.isPresent())
        {
            Account account = optionalAccount.get();
            Transaction transaction = new Transaction();
            transaction = transaction.depositTransaction(accountDto,account);
            account.setBalance(account.getBalance()+accountDto.getAmount());
            transactionRepo.save(transaction);
            accountRepo.save(account);
            return account;
        }
        else
        {
            throw new RuntimeException("Could not perform this operation.");
        }
    }

    @Override
    public AccountDto find(Integer id) {
        Optional<Account> optionalAccount = accountRepo.findById(id);
        if(optionalAccount.isPresent())
        {
            return new AccountDto(optionalAccount.get());
        }
        else
        {
            throw new RuntimeException("Account not found");
        }
    }

    @Override
    public Account withdraw(AccountDto accountDto) {
            Optional<Account> optionalAccount = accountRepo.findByAccNo(accountDto.getAccNo());
            if(optionalAccount.isPresent())
            {
                Account account = optionalAccount.get();
                if(account.getBalance()>accountDto.getAmount()+200)
                {
                    account.setBalance(account.getBalance()-accountDto.getAmount());
                    accountRepo.save(account);
                    return account;
                }
                else
                {
                    throw new RuntimeException("Insufficient balance");
                }
            }
            else
            {
                throw new RuntimeException("Could not find the account");
            }
    }

    @Override
    public AccountDto checkBalance(Integer accNo) {
        Account account = accountRepo.findByAccNo(accNo).get();
        AccountDto accountDto = new AccountDto();
        accountDto.setAccNo(account.getAccNo());
        accountDto.setAmount(account.getBalance());
        return (accountDto);
    }
}
