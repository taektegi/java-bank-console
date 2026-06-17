package com.ts.bank.repository;

import com.ts.bank.domain.Member;


public interface MemberRepository {
    public void save(Member member);
    public Member findbyId(Long id);
    public void deletebyId(Long id);
}
