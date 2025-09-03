package repository;

import model.Member;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberRepositoryImpl implements MemberRepository {

    private final List<Member> members = new ArrayList<>();
    private final String csvFileName = "members_inventory.csv";  // classpath-bestand

    public MemberRepositoryImpl() {
        loadFromCSV();
    }

    @Override
    public Member saveMember(Member member) {
        if (member.getMemberId() == null) {
            long newId = members.stream().mapToLong(Member::getMemberId).max().orElse(0) + 1;
            member.setMemberId(newId);
        } else {
            members.removeIf(m -> m.getMemberId().equals(member.getMemberId()));
        }
        members.add(member);
        saveToCSV();
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
    public boolean deleteMember(Long memberId) {
        boolean removed = members.removeIf(m -> m.getMemberId().equals(memberId));
        if (removed) saveToCSV();
        return removed;
    }

    // ================= CSV =================
    private void loadFromCSV() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(csvFileName)) {
            if (is == null) {
                System.out.println("❌ CSV bestand niet gevonden: " + csvFileName);
                return;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) { firstLine = false; continue; } // skip header
                String[] parts = line.split(",");
                if (parts.length != 7) continue;

                Member member = new Member();
                member.setMemberId(Long.parseLong(parts[0]));
                member.setMembershipNumber(parts[1]);
                member.setName(parts[2]);
                member.setStartYear(Integer.parseInt(parts[3]));
                member.setPhoneNumber(parts[4]);
                member.setEmail(parts[5]);
                member.setMembershipDate(LocalDate.parse(parts[6]));
                members.add(member);
            }
            System.out.println("✅ Members geladen uit CSV.");
        } catch (IOException e) {
            System.out.println("❌ Fout bij lezen CSV: " + e.getMessage());
        }
    }

    private void saveToCSV() {
        // Sla terug naar target/resources bij runtime kan lastig zijn, daarom pad in project gebruiken
        File file = new File("src/main/resources/" + csvFileName);
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
