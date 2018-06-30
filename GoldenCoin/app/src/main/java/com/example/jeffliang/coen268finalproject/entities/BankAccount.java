package com.example.jeffliang.coen268finalproject.entities;

public class BankAccount {

    public static final String TBL_NAME = "bank_account";

    public static final String COL_EMAIL = "email";
    public static final String COL_NAME_ON_CARD = "name_on_card";
    public static final String COL_CARD_NO = "card_no";
    public static final String COL_EXPIRE_MONTH = "expire_month";
    public static final String COL_EXPIRE_YEAR = "expire_year";
    public static final String COL_LAST_UPD_DT = "last_upd_dt";

    private String email;
    private String name_on_card;
    private String card_no;
    private String expire_month;
    private String expire_year;
    private String last_upd_dt;

    public BankAccount(String email, String name_on_card, String card_no, String expire_month, String expire_year) {
        this.email = email;
        this.name_on_card = name_on_card;
        this.card_no = card_no;
        this.expire_month = expire_month;
        this.expire_year = expire_year;
    }

    public BankAccount() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName_on_card() {
        return name_on_card;
    }

    public void setName_on_card(String name_on_card) {
        this.name_on_card = name_on_card;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getExpire_month() {
        return expire_month;
    }

    public void setExpire_month(String expire_month) {
        this.expire_month = expire_month;
    }

    public String getExpire_year() {
        return expire_year;
    }

    public void setExpire_year(String expire_year) {
        this.expire_year = expire_year;
    }

    public String getLast_upd_dt() {
        return last_upd_dt;
    }

    public void setLast_upd_dt(String last_upd_dt) {
        this.last_upd_dt = last_upd_dt;
    }

    @Override
    public String toString() {
        String result = " BankAccount Record values: \n"
                + "Name on Card: " + this.name_on_card + "\n"
                + "Card No: " + this.card_no + "\n"
                + "Expire Month: " + this.expire_month + "\n"
                + "Expire Year: " + this.expire_year;

        return result;
    }
}
