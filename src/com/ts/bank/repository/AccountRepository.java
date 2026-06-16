package com.ts.bank.repository;

import com.ts.bank.domain.Account;
import com.ts.bank.domain.Member;

import java.util.Map;
import java.util.HashMap;

public class AccountRepository {
    private final Map<Long, Account> accountRepository = new HashMap<>();

    public void save(Account account){
        accountRepository.put(account.getId(), account);
    }
    public Account findbyId(Long id){
        return accountRepository.get(id);
    }
    public Account[] findbyMemberId(Long memberId){
        Account[] accounts = new Account[100];
        int i = 0;
        for (Account account : accountRepository.values()) {
            if (memberId.equals(account.getMemberId())) {
                 accounts[i++] = account;
            }
        }
        return accounts;
    }
    public void deletebyId(Long id){
        accountRepository.remove(id);
    }

}
