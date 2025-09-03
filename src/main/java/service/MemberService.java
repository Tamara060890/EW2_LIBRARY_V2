package service;

import model.Member;
import repository.MemberRepository; // blijft hetzelfde


import java.util.List;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // Voeg een lid toe of update een bestaand lid
    public Member registerMember(Member member) {
        return memberRepository.saveMember(member);
    }

    // Zoek een lid op basis van ID
    public Member findMemberById(Long id) {
        return memberRepository.findByMemberId(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }

    // Zoek een lid op basis van e-mail
    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }

    // Haal alle leden op
    public List<Member> getAllMembers() {
        return memberRepository.findAllMembers();
    }

    // Verwijder een lid
    public void deleteMember(Long id) {
        memberRepository.deleteMember(id);
    }
}
