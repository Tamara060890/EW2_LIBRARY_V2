package repository;

import model.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member saveMember(Member member);

    Optional<Member> findByMemberId(Long memberId);

    Optional<Member> findByEmail(String email);

    List<Member> findAllMembers();

    boolean deleteMember(Long memberId); // boolean return om te checken of verwijderen lukt
}
