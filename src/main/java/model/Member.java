package model;

import java.time.LocalDate;

public class Member {

    private Long memberId;               // Specifiek voor Member
    private String name;
    private int startYear;
    private String phoneNumber;
    private String email;
    private String membershipNumber;
    private LocalDate membershipDate;

    // Standaard constructor
    public Member() {
    }

    // Volledige constructor
    public Member(Long memberId, String membershipNumber, String name, int startYear,
                  String phoneNumber, String email, LocalDate membershipDate) {
        this.memberId = memberId;
        this.membershipNumber = membershipNumber;
        this.name = name;
        this.startYear = startYear;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.membershipDate = membershipDate;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        Member member = (Member) o;
        return memberId != null && memberId.equals(member.memberId);
    }

    @Override
    public int hashCode() {
        return memberId != null ? memberId.hashCode() : 0;
    }
}