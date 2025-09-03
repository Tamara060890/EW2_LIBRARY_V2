package repository; // hetzelfde als MemberRepository

import model.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberRepositoryImpl implements MemberRepository {

    private final List<Member> members = new ArrayList<>();

    @Override
    public Member saveMember(Member member) {
        if (member.getMemberId() == null) {
            long newId = members.size() + 1L;
            member.setMemberId(newId);
        } else {
            members.removeIf(m -> m.getMemberId().equals(member.getMemberId()));
        }
        members.add(member);
        return member;
    }

    @Override
    public Optional<Member> findByMemberId(Long memberId) {
        return members.stream().filter(m -> m.getMemberId().equals(memberId)).findFirst();
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return members.stream().filter(m -> m.getEmail().equalsIgnoreCase(email)).findFirst();
    }

    @Override
    public List<Member> findAllMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public void deleteMember(Long memberId) {
        members.removeIf(m -> m.getMemberId().equals(memberId));
    }
}
