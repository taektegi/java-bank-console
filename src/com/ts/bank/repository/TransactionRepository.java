package com.ts.bank.repository;

import com.ts.bank.domain.Transaction;

public interface TransactionRepository {
    public Long nextId();
    public void save(Transaction transaction);
    public Transaction[] findbyAccountNumber(String accountNumber);

}
