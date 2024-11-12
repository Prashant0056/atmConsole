package com.example.atmConsole.service;

import com.example.atmConsole.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface TransactionService {
    ResponseEntity validate(Integer accNo, Integer pin);
}
