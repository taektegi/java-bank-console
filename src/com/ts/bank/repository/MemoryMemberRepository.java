package com.ts.bank.repository;

import com.ts.bank.domain.Member;
import java.util.Map;
import java.util.HashMap;

public class MemoryMemberRepository implements MemberRepository{
    private final Map<Long, Member> memberRepository = new HashMap<>();

    public void save(Member member){
        memberRepository.put(member.getId(), member);
    }

    public Member findbyId(Long id){
        return memberRepository.get(id);
    }
    public void deletebyId(Long id){
        memberRepository.remove(id);
    }

}
