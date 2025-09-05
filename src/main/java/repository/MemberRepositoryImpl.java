package repository;

import model.Member;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.nio.file.Files;

public class MemberRepositoryImpl implements MemberRepository {

    private final List<Member> members = new ArrayList<>();
    private final String csvFile = "data/members_inventory.csv"; // runtime CSV

    public MemberRepositoryImpl() {
        loadFromCSV();   // laad CSV bij start
    }

    // ================= Repository-methoden =================
    public Member saveMember(Member member) {
        if (member.getMemberId() == null) {
            // Alle bestaande IDs verzamelen en sorteren
            List<Long> existingIds = members.stream()
                    .map(Member::getMemberId)
                    .sorted()
                    .toList();

            long newId = 1;
            for (long id : existingIds) {
                if (id != newId) break; // eerste ontbrekende ID gevonden
                newId++;
            }
            member.setMemberId(newId);
        } else {
            // Als memberId bestaat -> overschrijven
            members.removeIf(m -> m.getMemberId().equals(member.getMemberId()));
        }

        members.add(member);
        saveToCSV();
        return member;
    }

    @Override
    public Optional<Member> findByMemberId(Long memberId) {
        return members.stream()
                .filter(m -> m.getMemberId().equals(memberId))
                .findFirst();
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
    public boolean deleteMember(Long memberId) {
        boolean removed = members.removeIf(m -> m.getMemberId().equals(memberId));
        if (removed) saveToCSV();
        return removed;
    }

    // ================= CSV =================

    /**
     * Laadt alle leden uit het CSV-bestand en vult de members-lijst.
     */
    private void loadFromCSV() {
        File file = new File(csvFile); // Verwijzing naar het CSV-bestand

        // Controleer of het bestand bestaat
        if (!file.exists()) {
            System.out.println("⚠️ CSV niet gevonden, maak een nieuw bestand bij eerste save.");
            return; // Stop met laden als er geen bestand is
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean firstLine = true; // Om de headerregel over te slaan
            members.clear(); // Leeg de huidige lijst zodat we opnieuw laden
            // Lees elke regel van het CSV-bestand
            while ((line = br.readLine()) != null) {
                // Sla de eerste regel (header) over
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] parts = line.split(","); // Splits de regel op komma's
                if (parts.length != 7) continue; // Sla regels over die niet 7 kolommen hebben
                // Maak een nieuw Member-object en vul de gegevens
                Member member = new Member();
                member.setMemberId(Long.parseLong(parts[0]));           // ID
                member.setMembershipNumber(parts[1]);                  // Membershipnummer
                member.setName(parts[2]);                               // Naam
                member.setStartYear(Integer.parseInt(parts[3]));       // Startjaar
                member.setPhoneNumber(parts[4]);                       // Telefoonnummer
                member.setEmail(parts[5]);                              // Email
                member.setMembershipDate(LocalDate.parse(parts[6]));    // Datum lidmaatschap
                // Voeg het Member-object toe aan de lijst
                members.add(member);
            }
            // Optioneel: print aantal geladen leden
            // System.out.println("✅ Members geladen uit CSV (" + members.size() + ")");
        } catch (IOException e) {
            System.out.println("❌ Fout bij lezen CSV: " + e.getMessage());
        }
    }

    private void saveToCSV() {
        File file = new File(csvFile);
        file.getParentFile().mkdirs(); // maak data/ aan als die nog niet bestaat

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("memberId,membershipNumber,name,startYear,phoneNumber,email,membershipDate\n");
            for (Member m : members) {
                bw.write(String.format("%d,%s,%s,%d,%s,%s,%s\n",
                        m.getMemberId(),
                        m.getMembershipNumber(),
                        m.getName(),
                        m.getStartYear(),
                        m.getPhoneNumber(),
                        m.getEmail(),
                        m.getMembershipDate()));
            }
        } catch (IOException e) {
            System.out.println("❌ Fout bij schrijven CSV: " + e.getMessage());
        }
    }
}
