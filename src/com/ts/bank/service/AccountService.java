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

    public Account createAccount(Long memberId) throws IllegalArgumentException{
        if(memberRepository.findbyId(memberId) == null){
            throw new IllegalArgumentException("존재하지 않는 회원입니다");
        }
        Account account = new Account(++sequence, "100"+sequence, memberId);
        accountRepository.save(account);
        return account;
    }
    public Account findAccountbyId(Long id){
        return accountRepository.findbyId(id);
    }
    public Account[] findAccountbyMemberId(Long memberId) throws IllegalArgumentException{
        if(memberRepository.findbyId(memberId) == null){
            throw new IllegalArgumentException("존재하지 않는 회원입니다");
        }
        return accountRepository.findbyMemberId(memberId);
    }
    public void deleteAccount(){

    }


}
