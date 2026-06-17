package com.ts.bank.repository;

import com.ts.bank.domain.Transaction;

import java.util.HashMap;
import java.util.Map;

public class MemoryTransactionRepository implements TransactionRepository{
    private final Map<Long, Transaction> transactionRepository = new HashMap<>();
    private Long sequence = 0L;

    public Long nextId(){
        return ++sequence;
    }
    public void save(Transaction transaction){
        transactionRepository.put(transaction.getId(),transaction);
    };
    public Transaction[] findbyAccountNumber(String accountNumber){
        Transaction[] transactions = new Transaction[100];
        int i = 0;
        for (Transaction transaction :transactionRepository.values()){
            if(transaction.getAccountNumber().equals(accountNumber)){
                transactions[i++] = transaction;
            }
        }
        return transactions;
    }

}
