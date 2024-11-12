package com.example.atmConsole.dto;

import com.example.atmConsole.model.Account;

import java.util.Iterator;
import java.util.Optional;

public class AccountDto {
    private Integer accNo;
    private Integer amount;

    public AccountDto( Integer accNo, Integer amount) {

        this.accNo = accNo;
        this.amount = amount;
    }

    public AccountDto() {
    }


    public AccountDto(Account account) {
        this.accNo =account.getAccNo();
        this.amount = account.getBalance();
    }

    public AccountDto(Integer amount) {
        this.amount = amount;
    }

    public Integer getAccNo() {
        return accNo;
    }

    public void setAccNo(Integer accNo) {
        this.accNo = accNo;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
