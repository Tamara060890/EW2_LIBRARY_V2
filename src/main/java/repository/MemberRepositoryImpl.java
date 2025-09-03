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

    // ================= Repository-methoden =================
    @Override
    public Member saveMember(Member member) {
        members.removeIf(m -> m.getMemberId().equals(member.getMemberId()));
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
        File file = new File(System.getProperty("user.home"), csvFileName);
        if (!file.exists()) {
            System.out.println("ℹ️ CSV bestand bestaat nog niet: " + file.getAbsolutePath());
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
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
        File file = new File(System.getProperty("user.home"), csvFileName);
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
            System.out.println("✅ Members opgeslagen naar CSV: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("❌ Fout bij schrijven CSV: " + e.getMessage());
        }
    }
}
