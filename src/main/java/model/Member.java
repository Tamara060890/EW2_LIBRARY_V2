package model;


import java.time.LocalDate;

public class Member {
    private Long memberId;              // Uniek ID van het lid
    private String memberName;          // Naam van het lid
    private String memberEmail;         // E-mailadres van het lid
    private String membershipNumber;    // Lidmaatschapsnummer
    private LocalDate membershipDate;   //


    // Lege constructor
    public Member() {
    }

    // Constructor om een lid aan te maken met alle gegevens.
    public Member(Long memberId, String memberName, String memberEmail, String membershipNumber, LocalDate membershipDate) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.membershipNumber = membershipNumber;
        this.membershipDate = membershipDate;
    }


    // Getters en setters
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMembershipNumber() {
        return membershipNumber;
    }

    public void setMembershipNumber(String membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    public LocalDate getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(LocalDate membershipDate) {
        this.membershipDate = membershipDate;
    }
}
