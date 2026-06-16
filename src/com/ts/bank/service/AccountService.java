package com.ts.bank.service;

import com.ts.bank.domain.Member;
import com.ts.bank.domain.Account;
import com.ts.bank.repository.MemberRepository;
import com.ts.bank.repository.AccountRepository;

public class AccountService {
    private final AccountRepository accountRepository;
    private final MemberRepository memberRepository;
    private Long sequence = 0L;

    public AccountService(AccountRepository accountRepository, MemberRepository memberRepository){
        this.accountRepository = accountRepository;
        this.memberRepository = memberRepository;
    }

    public Account createAccount(Long memberId){
        Account account = new Account(++sequence, "100"+sequence, memberId);
        accountRepository.save(account);
        return account;
    }
    public Account findAccountbyId(Long id){
        return accountRepository.findbyId(id);
    }
    public Account findAccountbyMemberId(Long memberId){
        return accountRepository.findbyMemberId(memberId);
    }


}
