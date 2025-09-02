package repository;

import model.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberRepositoryImpl implements MemberRepository{
    private List<Member> members = new ArrayList<>();


    @Override
    public Member saveMember(Member member) {
        // Als member met hetzelfde id bestaat → vervangen
        members.removeIf(m -> m.getMemberId().equals(member.getMemberId()));
        // Members toevoegen aan de lijst
        members.add(member);
        return member;
    }

    @Override
    public Optional<Member> findByMemberId(Long memberID) {
        // Zoekt het member met die id
        return members.stream()
                .filter(m -> m.getMemberId().equals(memberID))
                .findFirst();
    }

    @Override
    public Optional<Member> findByMemberEmail(String memberEmail) {
        // Zoekt member met dit e-mailadres
        return members.stream()
                .filter(m -> m.getMemberEmail().equalsIgnoreCase(memberEmail))
                .findFirst();
    }

    @Override
    public List<Member> findAllMembers() {
        // Nieuwe lijst teruggeven
        return new ArrayList<>(members);
    }

    @Override
    public void deleteMember(Long memberId) {
        // verwijdert alle members met dit id
        members.removeIf(m -> m.getMemberId().equals(memberId));
    }
}
