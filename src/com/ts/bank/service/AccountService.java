package com.ts.bank.service;

import com.ts.bank.domain.Account;
import com.ts.bank.domain.AccountStatus;
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

    public Account createAccount(Long memberId) {
        validateMember(memberId);
        Account account = new Account(++sequence, "100"+sequence, memberId);
        accountRepository.save(account);
        return account;
    }
    public Account findAccountbyId(Long id){
        return accountRepository.findbyId(id);
    }
    public Account[] findAccountbyMemberId(Long memberId) {
        validateMember(memberId);
        return accountRepository.findbyMemberId(memberId);
    }
    public Account findAccountbyAccountNumber(String accountNumber) {
        validateAccount(accountNumber);
        return accountRepository.findbyAccountNumber(accountNumber);
    }

    // 계좌 활성화, 정지, 해지
    public void activateAccount(String accountNumber){
        validateAccount(accountNumber);
        Account account = accountRepository.findbyAccountNumber(accountNumber);
        account.activate();
    }
    public void suspendAccount(String accountNumber){
        validateAccount(accountNumber);
        Account account = accountRepository.findbyAccountNumber(accountNumber);
        account.suspend();
    }
    public void closeAccount(String accountNumber){
        validateAccount(accountNumber);
        Account account = accountRepository.findbyAccountNumber(accountNumber);
        account.close();
    }

    // 입금, 출금, 이체
    public Long depositAccount(String accountNumber, Long cash){
        validateAccount(accountNumber);
        return accountRepository.findbyAccountNumber(accountNumber).deposit(cash);
    }
    public Long withdrawAccount(String accountNumber, Long cash){
        validateAccount(accountNumber);
        return accountRepository.findbyAccountNumber(accountNumber).withdraw(cash);
    }
    public Long transfer(String myAccount, String otherAccount, Long cash){
        validateAccount(myAccount);
        validateAccount(otherAccount);
        Long balance = accountRepository.findbyAccountNumber(myAccount).withdraw(cash);
        accountRepository.findbyAccountNumber(otherAccount).deposit(cash);
        return balance;
    }

    // 계좌, 회원 존재여부 확인
    public void validateAccount(String accountNumber){
        if(accountRepository.findbyAccountNumber(accountNumber) == null){
            throw new IllegalArgumentException("존재하지 않는 계좌입니다");
        }
    }
    public void validateMember(Long memberId){
        if(memberRepository.findbyId(memberId) == null){
            throw new IllegalArgumentException("존재하지 않는 회원입니다");
        }
    }

}
