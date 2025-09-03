package repository;

import model.Member;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface voor het beheren van Members.
 * Definieert de basis CRUD-operaties.
 */
public interface MemberRepository {

    /**
     * Voeg een nieuw lid toe of werk een bestaand lid bij.
     *
     * @param member Member object om op te slaan
     * @return opgeslagen Member
     */
    Member saveMember(Member member);

    /**
     * Zoek een lid op basis van interne ID.
     *
     * @param memberId ID van het lid
     * @return Optional van Member (leeg als niet gevonden)
     */
    Optional<Member> findByMemberId(Long memberId);

    /**
     * Zoek een lid op basis van e-mailadres.
     *
     * @param email e-mailadres van het lid
     * @return Optional van Member (leeg als niet gevonden)
     */
    Optional<Member> findByEmail(String email);

    /**
     * Haal alle leden op.
     *
     * @return lijst van alle Members
     */
    List<Member> findAllMembers();

    /**
     * Verwijder een lid op basis van ID.
     *
     * @param memberId ID van het lid dat verwijderd moet worden
     */
    void deleteMember(Long memberId);
}
