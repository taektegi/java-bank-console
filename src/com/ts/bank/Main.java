package com.ts.bank;

import java.util.Scanner;

import com.ts.bank.domain.Account;
import com.ts.bank.domain.Member;
import com.ts.bank.repository.MemoryAccountRepository;
import com.ts.bank.repository.MemoryMemberRepository;
import com.ts.bank.service.AccountService;
import com.ts.bank.service.MemberService;


public class Main {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MemoryMemberRepository memberRepo = new MemoryMemberRepository();
        MemberService memberService = new MemberService(memberRepo);
        MemoryAccountRepository accountRepo = new MemoryAccountRepository();
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
                    Account account = accountService.createAccount(id);
                    System.out.println("계좌가 생성되었습니다/ 계좌번호 : "+ account.getAccountNumber());

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
                try{

                    System.out.print("\n");
                    System.out.print("계좌번호를 입력해주세요 : ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("입금하실 금액을 입력해주세요 : ");
                    Long cash = Long.parseLong(scanner.nextLine());
                    Long balance = accountService.depositAccount(accountNumber,cash);
                    System.out.println(cash+"원 입금완료되었습니다/ 계좌잔액 : "+balance+"원");

                } catch(Exception e){
                    System.out.println(e.getMessage());
                }

            } else if(select.equals(7)){
                // 출금
                try{

                    System.out.print("\n");
                    System.out.print("계좌번호를 입력해주세요 : ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("출금하실 금액을 입력해주세요 : ");
                    Long cash = Long.parseLong(scanner.nextLine());
                    Long balance = accountService.withdrawAccount(accountNumber,cash);
                    System.out.println(cash+"원 출금완료되었습니다/ 계좌잔액 : "+balance+"원");

                } catch(Exception e){
                    System.out.println(e.getMessage());
                }

            } else if(select.equals(8)){
                // 이체
                try{

                    System.out.print("\n");
                    System.out.print("계좌번호를 입력해주세요 : ");
                    String myAccount = scanner.nextLine();
                    System.out.print("이체할 계좌번호를 입력해주세요 : ");
                    String otherAccount = scanner.nextLine();
                    System.out.print("이체하실 금액을 입력해주세요 : ");
                    Long cash = Long.parseLong(scanner.nextLine());
                    Long balance = accountService.transfer(myAccount,otherAccount,cash);
                    System.out.println(cash+"원 이체완료되었습니다/ 계좌잔액 : "+balance+"원");

                } catch(Exception e){
                    System.out.println(e.getMessage());
                }

            } else if(select.equals(9)){
                // 계좌정지
                try{

                    System.out.print("\n");
                    System.out.print("회원 ID를 입력해주세요 : ");
                    Long id = Long.parseLong(scanner.nextLine());
                    memberService.findMember(id);
                    System.out.print("정지시킬 계좌번호를 입력해주세요 : ");
                    String accountNumber = scanner.nextLine();
                    accountService.suspendAccount(accountNumber);
                    System.out.println("계좌상태 : "+ accountService.findAccountbyAccountNumber(accountNumber).getStatus());

                } catch(Exception e){
                    System.out.println(e.getMessage());
                }

            } else if(select.equals(10)){
                // 계좌활성화
                try{

                    System.out.print("\n");
                    System.out.print("회원 ID를 입력해주세요 : ");
                    Long id = Long.parseLong(scanner.nextLine());
                    memberService.findMember(id);
                    System.out.print("활성화시킬 계좌번호를 입력해주세요 : ");
                    String accountNumber = scanner.nextLine();
                    accountService.activateAccount(accountNumber);
                    System.out.println("계좌상태 : "+ accountService.findAccountbyAccountNumber(accountNumber).getStatus());

                } catch(Exception e){
                    System.out.println(e.getMessage());
                }

            } else if(select.equals(11)){
                // 계좌해지
                try{

                    System.out.print("\n");
                    System.out.print("회원 ID를 입력해주세요 : ");
                    Long id = Long.parseLong(scanner.nextLine());
                    memberService.findMember(id);
                    System.out.print("정지시킬 계좌번호를 입력해주세요 : ");
                    String accountNumber = scanner.nextLine();
                    accountService.closeAccount(accountNumber);
                    System.out.println("계좌상태 : "+ accountService.findAccountbyAccountNumber(accountNumber).getStatus());

                } catch(Exception e){
                    System.out.println(e.getMessage());
                }

            }else{
                System.out.println("보기에 제시된 숫자만 입력해주세요.");
            }

        } while(true);


    }
}