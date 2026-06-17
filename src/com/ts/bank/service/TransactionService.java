package com.ts.bank.service;

import com.ts.bank.repository.MemoryTransactionRepository;
import com.ts.bank.repository.TransactionRepository;
import com.ts.bank.domain.Transaction;

import java.util.List;

public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> findbyAccountNumber(String accountNumber){
        return transactionRepository.findbyAccountNumber(accountNumber);
    }
}
