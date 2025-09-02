package repository;
import model.Member;

import java.util.List;

import java.util.Optional;

public interface MemberRepository{
    // Member opslaan(nieuw toevoegen of bestaande updaten
    public Member saveMember(Member member);

    // Member zoeken op id
    public Optional<Member> findByMemberId(Long memberId);

    // Member zoeken op emailadres
    public Optional<Member> findByMemberEmail(String memberEmail);   // contactInfo gebruiken als email

    // Alle members ophalen
    public List<Member> findAllMembers();

    // Member verwijderen via id
    public void deleteMember(Long memberID);
}
