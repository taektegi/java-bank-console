package com.ts.bank.service;

import com.ts.bank.domain.*;
import com.ts.bank.repository.*;

import java.time.LocalDateTime;
import java.util.List;

public class AccountService {
    private final AccountRepository accountRepository;
    private final MemberRepository memberRepository;
    private final TransactionRepository transactionRepository;
    private Long sequence = 0L;

    public AccountService(AccountRepository accountRepository, MemberRepository memberRepository, TransactionRepository transactionRepository){
        this.accountRepository = accountRepository;
        this.memberRepository = memberRepository;
        this.transactionRepository = transactionRepository;
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
    public List<Account> findAccountbyMemberId(Long memberId) {
        validateMember(memberId);
        return accountRepository.findbyMemberId(memberId);
    }
    public Account findAccountbyAccountNumber(String accountNumber) {
        validateAccount(accountNumber);
        return accountRepository.findbyAccountNumber(accountNumber);
    }
    public Member findMemberbyAccountNumber(String accountNumber){
        return memberRepository.findbyId(findAccountbyAccountNumber(accountNumber).getMemberId());
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
        Long balance = accountRepository.findbyAccountNumber(accountNumber).deposit(cash);
        LocalDateTime createdAt = LocalDateTime.now();
        Long id = transactionRepository.nextId();
        Transaction transaction = new Transaction(id,accountNumber,null,createdAt,cash, TransactionType.DEPOSIT);
        transactionRepository.save(transaction);
        return balance;
    }
    public Long withdrawAccount(String accountNumber, Long cash){
        validateAccount(accountNumber);
        Long balance = accountRepository.findbyAccountNumber(accountNumber).withdraw(cash);
        LocalDateTime createdAt = LocalDateTime.now();
        Long id = transactionRepository.nextId();
        Transaction transaction = new Transaction(id,accountNumber,null,createdAt,cash, TransactionType.WITHDRAW);
        transactionRepository.save(transaction);
        return balance;
    }
    public Long transfer(String myAccount, String targetAccount, Long cash){
        validateAccount(myAccount);
        validateAccount(targetAccount);
        Account account1 = accountRepository.findbyAccountNumber(myAccount);
        Account account2 = accountRepository.findbyAccountNumber(targetAccount);
        account1.validateActive();
        account2.validateActive();

        Long balance = account1.withdraw(cash);
        account2.deposit(cash);

        LocalDateTime createdAt = LocalDateTime.now();
        Long id1 = transactionRepository.nextId();
        Transaction transaction1 = new Transaction(id1,myAccount,targetAccount,createdAt,cash, TransactionType.TRANSFER_OUT);
        Long id2 = transactionRepository.nextId();
        Transaction transaction2 = new Transaction(id2,targetAccount,myAccount,createdAt,cash, TransactionType.TRANSFER_IN);
        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);

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
