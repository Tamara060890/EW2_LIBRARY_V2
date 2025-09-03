import model.Member;
import repository.MemberRepository;
import repository.MemberRepositoryImpl;
import service.MemberService;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MainAppMember {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String MEMBER_CSV_FILE = "resources/members_inventory.csv";

    public static void main(String[] args) {
        // Repository en Service aanmaken
        MemberRepository memberRepo = new MemberRepositoryImpl();
        MemberService memberService = new MemberService(memberRepo);

        // CSV import bij opstart
        loadMembersFromCSV(MEMBER_CSV_FILE, memberService);

        boolean running = true;

        System.out.println("=================================");
        System.out.println("   📚 Welcome to the Library! 📚  ");
        System.out.println("=================================");

        while (running) {
            printMainMenu();
            int choice = readChoice();

            switch (choice) {
                case 1 -> booksMenu();
                case 2 -> membersMenu(memberService);
                case 3 -> loansMenu();
                case 0 -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    // ---------- MAIN MENU ----------
    private static void printMainMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Books");
        System.out.println("2. Members");
        System.out.println("3. Loans");
        System.out.println("0. Exit");
        System.out.print("Your choice: ");
    }

    // ---------- BOOKS MENU ----------
    private static void booksMenu() {
        System.out.println("--- Books Menu (not implemented yet) ---");
    }

    // ---------- MEMBERS MENU ----------
    private static void membersMenu(MemberService memberService) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Members Menu ---");
            System.out.println("1. Add Member");
            System.out.println("2. Remove Member");
            System.out.println("3. Search Member by Email");
            System.out.println("4. Show All Members");
            System.out.println("0. Back");
            System.out.print("Your choice: ");

            int choice = readChoice();
            switch (choice) {
                case 1 -> addMember(memberService);
                case 2 -> removeMember(memberService);
                case 3 -> searchMemberByEmail(memberService);
                case 4 -> showAllMembers(memberService);
                case 0 -> back = true;
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static void addMember(MemberService memberService) {
        try {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter age: ");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter phone: ");
            String phone = scanner.nextLine();
            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            String membershipNumber = "M" + System.currentTimeMillis();

            Member member = new Member(null, membershipNumber, name, age, phone, email, LocalDate.now());
            memberService.registerMember(member);

            // CSV bijwerken
            saveMembersToCSV(MEMBER_CSV_FILE, memberService);

            System.out.println("✅ Member added: " + name);
        } catch (Exception e) {
            System.out.println("❌ Error adding member: " + e.getMessage());
        }
    }

    private static void removeMember(MemberService memberService) {
        try {
            System.out.print("Enter member ID to delete: ");
            Long id = Long.parseLong(scanner.nextLine());
            memberService.deleteMember(id);

            // CSV bijwerken
            saveMembersToCSV(MEMBER_CSV_FILE, memberService);

            System.out.println("✅ Member deleted.");
        } catch (Exception e) {
            System.out.println("❌ Error deleting member: " + e.getMessage());
        }
    }

    private static void searchMemberByEmail(MemberService memberService) {
        try {
            System.out.print("Enter member email: ");
            String email = scanner.nextLine();
            Member m = memberService.findMemberByEmail(email);
            System.out.println("✅ Found: " + m.getName() + " (" + m.getMembershipNumber() + ")");
        } catch (Exception e) {
            System.out.println("❌ Member not found.");
        }
    }

    private static void showAllMembers(MemberService memberService) {
        System.out.println("\n=== All Members ===");
        List<Member> members = memberService.getAllMembers();
        if (members.isEmpty()) {
            System.out.println("No members found.");
        } else {
            for (Member m : members) {
                System.out.println(m.getMemberId() + " - " + m.getMembershipNumber() + " - " +
                        m.getName() + " (" + m.getEmail() + ")");
            }
        }
    }

    // ---------- CSV IMPORT ----------
    private static void loadMembersFromCSV(String filePath, MemberService memberService) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                Long memberId = Long.parseLong(fields[0]);
                String membershipNumber = fields[1];
                String name = fields[2];
                int age = Integer.parseInt(fields[3]);
                String phoneNumber = fields[4];
                String email = fields[5];
                LocalDate membershipDate = LocalDate.parse(fields[6]);

                Member member = new Member(memberId, membershipNumber, name, age, phoneNumber, email, membershipDate);
                memberService.registerMember(member);
            }
        } catch (FileNotFoundException e) {
            // CSV file niet gevonden, start met lege lijst
        } catch (IOException e) {
            System.out.println("❌ Error reading CSV: " + e.getMessage());
        }
    }

    // ---------- CSV EXPORT (wordt automatisch bijwerken) ----------
    private static void saveMembersToCSV(String filePath, MemberService memberService) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("memberId,membershipNumber,name,age,phoneNumber,email,membershipDate");
            bw.newLine();
            for (Member m : memberService.getAllMembers()) {
                bw.write(m.getMemberId() + "," +
                        m.getMembershipNumber() + "," +
                        m.getName() + "," +
                        m.getAge() + "," +
                        m.getPhoneNumber() + "," +
                        m.getEmail() + "," +
                        m.getMembershipDate());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Error saving CSV: " + e.getMessage());
        }
    }

    // ---------- UTILITY ----------
    private static int readChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // ---------- LOANS MENU ----------
    private static void loansMenu() {
        System.out.println("--- Loans Menu (not implemented yet) ---");
    }
}
