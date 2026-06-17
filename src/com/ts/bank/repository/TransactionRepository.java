package com.ts.bank.repository;

import com.ts.bank.domain.Transaction;
import java.util.List;

public interface TransactionRepository {
    public Long nextId();
    public void save(Transaction transaction);
    public List<Transaction> findbyAccountNumber(String accountNumber);

}
