package com.ts.bank.domain;

import java.time.LocalDateTime;

public class Transaction {

    private final Long id;
    private final String accountNumber;
    private final String targetAccountNumber;
    private final LocalDateTime createdAt;
    private final Long amount;
    private final TransactionType type;

    public Transaction(Long id, String accountNumber, String targetAccountNumber, LocalDateTime createdAt, Long amount, TransactionType type){
        this.id = id;
        this.accountNumber = accountNumber;
        this.targetAccountNumber = targetAccountNumber;
        this.createdAt = createdAt;
        this.amount = amount;
        this.type = type;
    }

    public Long getId(){
        return this.id;
    }
    public String getAccountNumber(){
        return this.accountNumber;
    }
    public String getTargetAccountNumber(){
        return this.targetAccountNumber;
    }
    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }
    public Long getAmount(){
        return this.amount;
    }
    public TransactionType getType(){
        return this.type;
    }


}
