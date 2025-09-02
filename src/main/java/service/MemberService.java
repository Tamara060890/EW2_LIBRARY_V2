package service;

import model.Member;
import repository.MemberRepository;

import java.util.NoSuchElementException;

public class MemberService {
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member registerMember(Member member){
        return memberRepository.saveMember(member);
    }

//    public Member findMemberById(Long memberId){
//       return memberRepository.findByMemberId(memberId)
//                .orElseThrow()) -> new NoSuchElementException();
//   }
//
//    public Member findMemberByEmail(String memberEmail){}



}
