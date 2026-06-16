package com.ts.bank.domain;

public class Account {
    private final Long id;
    private final String accountNumber;
    private final Long memberId;
    private Long balance;

    public Account(Long id, String accountNumber, Long memberId){
        this.id = id;
        this.accountNumber = accountNumber;
        this.memberId = memberId;
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
}
