package service;

import model.Member;
import repository.MemberRepository;

import java.util.List;

public class MemberService {
    private long lastMemberId = 0; // houdt bij wat de hoogste ID is

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member addMember(Member member) {
        validateMember(member);

        // Als het ID null is (nieuw lid), geef automatisch een oplopend ID
        if (member.getMemberId() == null) {
            member.setMemberId(++lastMemberId);
        } else {
            // update lastMemberId als CSV-ID hoger is
            if (member.getMemberId() > lastMemberId) {
                lastMemberId = member.getMemberId();
            }
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
    }

    public static class MemberNotFoundException extends RuntimeException {
        public MemberNotFoundException(String message) {
            super(message);
        }
    }
}
