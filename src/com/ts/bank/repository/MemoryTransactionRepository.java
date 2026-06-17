package com.ts.bank.repository;

import com.ts.bank.domain.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public List<Transaction> findbyAccountNumber(String accountNumber){
        List<Transaction> transactions = new ArrayList<>();

        for (Transaction transaction :transactionRepository.values()){
            if(transaction.getAccountNumber().equals(accountNumber)){
                transactions.add(transaction);
            }
        }
        return transactions;
    }

}
