package com.example.atmConsole.model;

import com.example.atmConsole.dto.AccountDto;
import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;


    private enum TransactionType{
        DEPOSIT,
        WITHDRAW
    }

    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private Date date;


    public Transaction(Integer accNo) {
        this.account.setAccNo(accNo);
    }

    public Transaction() {
        this.date = new Date();
    }

    public Transaction depositTransaction(AccountDto accountDto,Account account)
    {
        Transaction newTransaction = new Transaction();
        newTransaction.setAccount(account);
        newTransaction.setAmount(accountDto.getAmount());
        newTransaction.setType(TransactionType.DEPOSIT);
        return newTransaction;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
