package com.ts.bank.service;

import com.ts.bank.domain.Account;
import com.ts.bank.repository.MemoryMemberRepository;
import com.ts.bank.repository.MemoryAccountRepository;

public class AccountService {
    private final MemoryAccountRepository accountRepository;
    private final MemoryMemberRepository memberRepository;
    private Long sequence = 0L;

    public AccountService(MemoryAccountRepository accountRepository, MemoryMemberRepository memberRepository){
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
    public Account findAccountbyAccountNumber(String accountNumber) throws IllegalArgumentException{
        if(accountRepository.findbyAccountNumber(accountNumber) == null){
            throw new IllegalArgumentException("존재하지 않는 계좌입니다");
        }
        return accountRepository.findbyAccountNumber(accountNumber);
    }

    public void activateAccount(String accountNumber){
        Account account = accountRepository.findbyAccountNumber(accountNumber);
        account.activate();
    }
    public void suspendAccount(String accountNumber){
        Account account = accountRepository.findbyAccountNumber(accountNumber);
        account.suspend();
    }
    public void closeAccount(String accountNumber){
        Account account = accountRepository.findbyAccountNumber(accountNumber);
        account.close();
    }


}
