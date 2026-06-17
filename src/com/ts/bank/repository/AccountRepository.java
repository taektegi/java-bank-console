package com.ts.bank.repository;

import com.ts.bank.domain.Account;

import java.util.List;

public interface AccountRepository {
    public void save(Account account);
    public Account findbyId(Long id);
    public List<Account> findbyMemberId(Long memberId);
    public Account findbyAccountNumber(String accountNumber);
    public void deletebyId(Long id);
}
