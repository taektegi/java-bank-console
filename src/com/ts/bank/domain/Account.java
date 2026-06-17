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
        if(this.status.equals(AccountStatus.CLOSED)){
            throw new IllegalStateException("해지된 계좌입니다");
        }
        this.status = AccountStatus.ACTIVE;
    }
    public void suspend(){
        if(this.status.equals(AccountStatus.CLOSED)){
            throw new IllegalStateException("해지된 계좌입니다");
        }
        this.status = AccountStatus.SUSPENDED;
    }
    public void close(){
        if(balance > 0){
            throw new IllegalStateException("계좌에 잔액이 존재합니다 잔액을 비우고 해지를 시도해주세요");
        }
        this.status = AccountStatus.CLOSED;

    }

    public Long deposit(Long cash){
        validateActive();
        validatePositiveCash(cash);
        this.balance += cash;
        return this.balance;
    }
    public Long withdraw(Long cash){
        validateActive();
        validatePositiveCash(cash);
        validateEnoughBalance(cash);
        this.balance -= cash;
        return this.balance;
    }

    public void validateActive() {
        if(this.status != AccountStatus.ACTIVE){
            throw new IllegalStateException("계좌가 활성화상태가 아닙니다");
        }
    }
    private void validateEnoughBalance(Long cash) {
        if(this.balance < cash) {
            throw new IllegalArgumentException("잔액이 부족합니다");
        }
    }
    private void validatePositiveCash(Long cash){
        if(cash <= 0L){
            throw new IllegalArgumentException("금액은 0보다 커야합니다");
        }
    }


}
