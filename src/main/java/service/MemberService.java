package service;

import model.Member;
import repository.MemberRepository;

import java.time.LocalDate;
import java.util.List;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member addMember(Member member) {
        validateMember(member);

        // StartYear automatisch invullen
        if (member.getStartYear() == 0) {
            member.setStartYear(LocalDate.now().getYear());
        }

        // Eerst opslaan om een memberId te genereren
        Member savedMember = memberRepository.saveMember(member);

        // MembershipNumber automatisch genereren als het nog leeg is
        if (savedMember.getMembershipNumber() == null || savedMember.getMembershipNumber().isBlank()) {
            savedMember.setMembershipNumber(String.format("MBR-%d-%03d",
                    LocalDate.now().getYear(), savedMember.getMemberId()));
            // Opslaan met het nieuwe membershipNumber
            savedMember = memberRepository.saveMember(savedMember);
        }

        return savedMember;
    }
    public Member updateMember(Member member) {
        validateMember(member);
        if (member.getMemberId() == null || memberRepository.findByMemberId(member.getMemberId()).isEmpty()) {
            throw new MemberNotFoundException("Cannot update. Member not found with ID: " + member.getMemberId());
        }
        return memberRepository.saveMember(member);
    }

    public void removeMember(Long id) {
        boolean deleted = memberRepository.deleteMember(id);
        if (!deleted) throw new MemberNotFoundException("Cannot delete. Member not found with ID: " + id);
    }

    public Member searchMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with email: " + email));
    }

    public Member findMemberById(Long id) {
        return memberRepository.findByMemberId(id)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with ID: " + id));
    }

    public List<Member> showAllMembers() {
        return memberRepository.findAllMembers();
    }

    private void validateMember(Member member) {
        if (member.getName() == null || member.getName().isBlank())
            throw new IllegalArgumentException("Member name cannot be empty");
        if (member.getEmail() == null || member.getEmail().isBlank())
            throw new IllegalArgumentException("Member email cannot be empty");
        if (!member.getEmail().contains("@"))
            throw new IllegalArgumentException("Invalid email address");
        if (member.getPhoneNumber() == null || member.getPhoneNumber().isBlank())
            throw new IllegalArgumentException("Phone number cannot be empty");
    }

    public static class MemberNotFoundException extends RuntimeException {
        public MemberNotFoundException(String message) {
            super(message);
        }
    }
}
