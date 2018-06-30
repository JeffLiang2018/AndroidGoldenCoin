package com.example.jeffliang.coen268finalproject.entities;

import android.support.annotation.NonNull;

import com.example.jeffliang.coen268finalproject.util.DateStringConvert;

import java.text.ParseException;
import java.util.Date;

public class TradeRecord implements Comparable{

    public static final String TBL_NAME = "trade_record";
    public static final String COL_EMAIL = "email";
    public static final String COL_COIN_NAME = "coin_name";
    public static final String COL_COIN_ACRONYM = "coin_acronym";
    public static final String COL_BUY_SELL_FLAG = "buy_sell_flag";
    public static final String COL_UNIT_PRICE = "unit_price";
    public static final String COL_COIN_AMOUNT = "coin_amount";
    public static final String COL_MONEY_AMOUNT = "money_account";
    public static final String COL_TRAN_DATE = "tran_date";

    public static final int SELL_VALUE = 0;
    public static final int BUY_VALUE = 1;

    private String email;
    private String coin_name;
    private String coin_acronym;
    private int buy_sell_flag;
    private double unit_price;
    private double amount;
    private double money_account;
    private String tran_date;

    public TradeRecord(String email, String coin_name, String coin_acronym, int buy_sell_flag
                    , double unit_price, double amount, double money_account, String tran_date) {
        this.email = email;
        this.coin_name = coin_name;
        this.coin_acronym = coin_acronym;
        this.buy_sell_flag = buy_sell_flag;
        this.unit_price = unit_price;
        this.amount = amount;
        this.money_account = money_account;
        this.tran_date = tran_date;
    }

    public TradeRecord() {
    }

    public String getCoin_name() {
        return coin_name;
    }

    public void setCoin_name(String coin_name) {
        this.coin_name = coin_name;
    }

    public String getCoin_acronym() {
        return coin_acronym;
    }

    public void setCoin_acronym(String coin_acronym) {
        this.coin_acronym = coin_acronym;
    }

    public int getBuy_sell_flag() {
        return buy_sell_flag;
    }

    public void setBuy_sell_flag(int buy_sell_flag) {
        this.buy_sell_flag = buy_sell_flag;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getMoney_account() {
        return money_account;
    }

    public void setMoney_account(double money_account) {
        this.money_account = money_account;
    }

    public String getTran_date() {
        return tran_date;
    }

    public void setTran_date(String tran_date) {
        this.tran_date = tran_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        if (o instanceof  TradeRecord) {
            TradeRecord tr = (TradeRecord)o;
            try {
                Date date1 = DateStringConvert.stringConvertToDate(this.tran_date);
                Date date2 = DateStringConvert.stringConvertToDate(tr.getTran_date());
                return date2.compareTo(date1);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return -1;
    }

    @Override
    public String toString() {
        String result = "TradeRecord Record values: \n"
                      + "Email: " + this.email + "\n"
                      + "Coin Name: " + this.coin_name + "\n"
                      + "Coin Acronym: " + this.coin_acronym + "\n"
                      + "Buy Sell Flag: " + this.buy_sell_flag + "\n"
                      + "Unit Price: " + this.unit_price + "\n"
                      + "Coin Amount: " + this.amount + "\n"
                      + "Money Account: " + this.money_account + "\n"
                      + "Trade Date: " + this.tran_date;
        return result;
    }
}
