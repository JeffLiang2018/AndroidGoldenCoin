package com.example.jeffliang.coen268finalproject.entities;

public class UserProfile {

    public final static String TBL_NAME = "user_profile";

    public final static String COL_EMAIL = "email";
    public final static String COL_LAST_NAME = "last_name";
    public final static String COL_FIRST_NAME = "first_name";
    public final static String COL_PHONE_NO = "phone_no";
    public final static String COL_DOB = "dob";
    public final static String COL_ADDR_1 = "addr_1";
    public final static String COL_ADDR_2 = "addr_2";
    public final static String COL_STATE = "state";
    public final static String COL_COUNTRY = "country";
    public final static String COL_SSN = "ssn";
    public final static String COL_LAST_UPD_DT = "last_upd_dt";

    private String email;
    private String surname;
    private String firstname;
    private String phoneno;
    private String dob;
    private String addr_1;
    private String addr_2;
    private String state;
    private String country;
    private String ssn;
    private String last_upd_dt;

    public UserProfile(String email, String surname, String firstname, String phoneno
                     , String dob, String addr_1, String addr_2, String state
                     , String country, String ssn) {
        this.email = email;
        this.surname = surname;
        this.firstname = firstname;
        this.phoneno = phoneno;
        this.dob = dob;
        this.addr_1 = addr_1;
        this.addr_2 = addr_2;
        this.state = state;
        this.country = country;
        this.ssn = ssn;
    }

    public UserProfile() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddr_1() {
        return addr_1;
    }

    public void setAddr_1(String addr_1) {
        this.addr_1 = addr_1;
    }

    public String getAddr_2() {
        return addr_2;
    }

    public void setAddr_2(String addr_2) {
        this.addr_2 = addr_2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getLast_upd_dt() {
        return last_upd_dt;
    }

    public void setLast_upd_dt(String last_upd_dt) {
        this.last_upd_dt = last_upd_dt;
    }

    @Override
    public String toString() {
        String result = "UserProfile Record Values: \n"
                + "Email: " + this.email + "\n"
                + "Last Name: " + this.surname + "\n"
                + "First Name: " + this.firstname + "\n"
                + "Phone No: " + this.phoneno + "\n"
                + "Date of Birth: " + this.dob + "\n"
                + "Addr 1: " + this.addr_1 + "\n"
                + "Addr 2: " + this.addr_2 + "\n"
                + "State: " + this.state + "\n"
                + "Country: " + this.country;
        return result;
    }
}
