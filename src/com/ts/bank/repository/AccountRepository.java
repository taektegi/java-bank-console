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
    public Account findbyMemberId(Long memberId){
        for (Account account : accountRepository.values()) {
            if (memberId.equals(account.getMemberId())) {
                return account;
            }
        }
        return null;
    }
    public void deletebyId(Long id){
        accountRepository.remove(id);
    }

}
