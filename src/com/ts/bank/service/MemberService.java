package com.ts.bank.service;


import com.ts.bank.domain.Member;
import com.ts.bank.repository.MemberRepository;
import com.ts.bank.repository.MemoryMemberRepository;


public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public void join(Member member){
        memberRepository.save(member);
    }
    public Member findMember(Long id) {
        if(memberRepository.findbyId(id) == null){
            throw new IllegalArgumentException("회원정보가 없습니다");
        }
        return memberRepository.findbyId(id);
    }
    public void deleteMember(Long id) {
        if(memberRepository.findbyId(id)==null){
            throw new IllegalArgumentException("존재하지 않는 회원입니다");
        }
        memberRepository.deletebyId(id);
    }

}
