package com.ts.bank.domain;

public class Account {
    private final Long id;
    private final String accountNumber;
    private final Long memberId;
    private Long balance;
    private AccountStatus status;

    public Account(Long id, String accountNumber, Long memberId){
        this.id = id;
        this.accountNumber = accountNumber;
        this.memberId = memberId;
        this.balance = 0L;
        this.status = AccountStatus.ACTIVE;
    }

    public Long getId() {
        return id;
    }
    public String getAccountNumber(){
        return accountNumber;
    }
    public Long getMemberId(){
        return memberId;
    }
    public AccountStatus getStatus() {
        return status;
    }

    public void activate(){
        this.status = AccountStatus.ACTIVE;
    }
    public void suspend(){
        this.status = AccountStatus.SUSPENDED;
    }
    public void close(){
        this.status = AccountStatus.CLOSED;
    }

    public void addBalance(Long cash){
        balance+=cash;
    }
    public void subBalance(Long cash){
        balance-=cash;
    }

}
