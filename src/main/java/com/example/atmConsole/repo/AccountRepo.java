package com.example.atmConsole.repo;

import com.example.atmConsole.model.Account;
import com.example.atmConsole.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account,Integer> {
    Optional<Account> findByAccNo(Integer accNo);

}
