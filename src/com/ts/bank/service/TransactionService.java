package com.ts.bank.service;

import com.ts.bank.repository.MemoryTransactionRepository;
import com.ts.bank.repository.TransactionRepository;
import com.ts.bank.domain.Transaction;

public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    public Transaction[] findbyAccountNumber(String accountNumber){
        return transactionRepository.findbyAccountNumber(accountNumber);
    }
}
