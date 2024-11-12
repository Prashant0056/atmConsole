package com.example.atmConsole.service;


import com.example.atmConsole.dto.ResponseDto;
import com.example.atmConsole.model.Account;
import com.example.atmConsole.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    AccountRepo accountRepo;

    @Override
    public ResponseEntity validate(Integer accNo, Integer pin) {
        Optional<Account> optionalAccount = accountRepo.findByAccNo(accNo);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
                if (Objects.equals(account.getPin(), pin)) {
                    return ResponseEntity.ok().body(Map.of("status","ok","message",accNo,"customer id",account.getCustomer().getId()));
                } else {
                    return ResponseEntity.ok().body(Map.of("status","pin error","message","not ok"));
                }

        }
        else
        {
            return ResponseEntity.ok().body(Map.of("status","not found","message","acc no not found"));

        }

    }
}
