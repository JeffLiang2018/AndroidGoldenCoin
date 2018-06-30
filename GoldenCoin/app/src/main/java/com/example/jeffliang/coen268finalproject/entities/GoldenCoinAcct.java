package com.example.jeffliang.coen268finalproject.entities;

public class GoldenCoinAcct {

    public static final String TBL_NAME = "golden_coin_acct";

    public static final String COL_EMAIL = "email";
    public static final String COL_BALANCE = "balance";
    public static final String COL_LAST_UPD_DT = "last_upd_dt";

    private String email;
    private double balance;
    private String last_upd_dt;

    public GoldenCoinAcct(String email, double balance) {
        this.email = email;
        this.balance = balance;
    }

    public GoldenCoinAcct() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getLast_upd_dt() {
        return last_upd_dt;
    }

    public void setLast_upd_dt(String last_upd_dt) {
        this.last_upd_dt = last_upd_dt;
    }


    @Override
    public String toString() {
        String result = "GoldenCoinAcct Record value: \n"
                     + "Email: " + this.email + "\n"
                     + "Balance: " + this.balance;
        return result;
    }
}
