package com.ts.bank.service;


import com.ts.bank.domain.Member;
import com.ts.bank.domain.Account;
import com.ts.bank.repository.MemberRepository;
import com.ts.bank.repository.AccountRepository;


public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public void join(Member member){
        memberRepository.save(member);
    }
    public Member findMember(Long id){
        return memberRepository.findbyId(id);
    }
    public void deleteMember(Long id){
        memberRepository.deletebyId(id);
    }

}
