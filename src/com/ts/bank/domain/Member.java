package com.ts.bank.domain;

public class Member {
    private final String name;
    private final Long id;
    private final String phoneNumber;

    public Member(Long id, String name, String phoneNumber){
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Long getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
}
