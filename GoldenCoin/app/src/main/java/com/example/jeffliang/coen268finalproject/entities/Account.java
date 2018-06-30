package com.example.jeffliang.coen268finalproject.entities;

public class Account {
    public final static String TBL_NAME = "account";

    public final static String COL_EMAIL = "email";
    public final static String COL_PASSWORD = "password";
    public final static String COL_LAST_UPD_DT = "last_upd_dt";

    private String email;
    private String password;
    private String last_upd_dt;

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Account() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLast_upd_dt() {
        return last_upd_dt;
    }

    public void setLast_upd_dt(String last_upd_dt) {
        this.last_upd_dt = last_upd_dt;
    }

    @Override
    public String toString() {
        String result = "Account Record values: \n"
                + "Email: " + this.email + "\n"
                + "Password: " + this.password + "\n"
                + "last_upd_dt: " + this.last_upd_dt;
        return result;
    }
}
