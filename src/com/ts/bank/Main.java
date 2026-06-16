package com.ts.bank;

import java.util.Scanner;

import com.ts.bank.domain.Account;
import com.ts.bank.domain.Member;
import com.ts.bank.repository.AccountRepository;
import com.ts.bank.repository.MemberRepository;
import com.ts.bank.service.AccountService;
import com.ts.bank.service.MemberService;


public class Main {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MemberRepository memberRepo = new MemberRepository();
        MemberService memberService = new MemberService(memberRepo);
        AccountRepository accountRepo = new AccountRepository();
        AccountService accountService = new AccountService(accountRepo, memberRepo);


        do{
            System.out.print("\n");
            System.out.println("1.회원가입  2.계좌생성  3.회원조회  4.계좌조회    5.회원탈퇴");
            System.out.println("6.입금     7.출금     8.이체     9.계좌정지   10.계좌활성화  11.계좌해지");
            System.out.print("필요한 서비스 번호를 입력해주세요 : ");
            Integer select = Integer.parseInt(scanner.nextLine());

            if(select.equals(1)){
                // 회원가입
                System.out.print("\n");
                System.out.print("이름을 입력해주세요 : ");
                String name = scanner.nextLine();
                System.out.print("전화번호를 입력해주세요 : ");
                String phoneNumber = scanner.nextLine();
                System.out.print("사용할 ID를 입력해주세요 : ");
                Long id = Long.parseLong(scanner.nextLine());

                Member m1 = new Member(id, name, phoneNumber);
                memberService.join(m1);

            } else if(select.equals(2)){
                // 계좌생성
                try{

                    System.out.print("\n");
                    System.out.print("회원 ID를 입력해주세요 : ");
                    Long id = Long.parseLong(scanner.nextLine());
                    accountService.createAccount(id);

                }catch(Exception e){
                    System.out.println(e.getMessage());
                }

            } else if(select.equals(3)){
                // 회원조회
                try{

                    System.out.print("\n");
                    System.out.print("회원 ID를 입력해주세요 : ");
                    Long id = Long.parseLong(scanner.nextLine());
                    Member member = memberService.findMember(id);
                    System.out.println("ID : "+member.getId()+"/ 회원명 : "+member.getName()+"/ 전화번호 : "+member.getPhoneNumber());

                } catch(Exception e){
                    System.out.println(e.getMessage());
                }

            } else if(select.equals(4)){
                // 계좌조회
                System.out.print("\n");
                try{

                    System.out.print("회원 ID를 입력해주세요 : ");
                    Long id = Long.parseLong(scanner.nextLine());
                    Account[] accounts = accountService.findAccountbyMemberId(id);
                    System.out.print("계좌번호 : ");
                    for(Account account : accounts){
                        if(account == null) break;
                        System.out.print(account.getAccountNumber()+", ");
                    }
                    System.out.println("/ 예금주 : "+memberService.findMember(id).getName());

                } catch(Exception e){
                    System.out.println(e.getMessage());
                }


            } else if(select.equals(5)){
                // 회원탈퇴
                try{
                    System.out.print("\n");
                    System.out.print("회원 ID를 입력해주세요 : ");
                    Long id = Long.parseLong(scanner.nextLine());
                    memberService.deleteMember(id);
                    System.out.println("회원 탈퇴처리 완료되었습니다");
                } catch(Exception e){
                    System.out.println(e.getMessage());
                }


            } else if(select.equals(6)){
                // 입금


            } else{
                System.out.println("보기에 제시된 숫자만 입력해주세요.");
            }
        } while(true);


    }
}