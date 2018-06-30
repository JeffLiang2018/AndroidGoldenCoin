package com.example.jeffliang.coen268finalproject.entities;

import android.support.annotation.NonNull;

import com.example.jeffliang.coen268finalproject.util.DateStringConvert;

import java.text.ParseException;
import java.util.Date;

public class Notification implements Comparable{

    public static final String TBL_NAME = "notification";

    public static final String COL_EMAIL = "email";
    public static final String COL_TITLE = "title";
    public static final String COL_CONTENT = "content";
    public static final String COL_GEN_DATE = "gen_date";
    public static final String COL_READ = "read";
    public static final String COL_LAST_UPD_DT = "last_upd_dt";

    public static final String READ = "1";
    public static final String UNREAD = "0";

    private String email;
    private String title;
    private String content;
    private String gen_date;
    private String read;
    private String last_upd_dt;

    public Notification(String email, String title, String content, String gen_date, String read) {
        this.email = email;
        this.title = title;
        this.content = content;
        this.gen_date = gen_date;
        this.read = read;
    }

    public Notification() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGen_date() {
        return gen_date;
    }

    public void setGen_date(String gen_date) {
        this.gen_date = gen_date;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getLast_upd_dt() {
        return last_upd_dt;
    }

    public void setLast_upd_dt(String last_upd_dt) {
        this.last_upd_dt = last_upd_dt;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        if (o instanceof Notification) {
            Notification notification = (Notification)o;
            try {
                Date date1 = DateStringConvert.stringConvertToDate(this.gen_date);
                Date date2 = DateStringConvert.stringConvertToDate(notification.getGen_date());
                return date2.compareTo(date1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        String result = "Notification Record values: \n"
                + "Email: " + this.email + "\n"
                + "Title: " + this.title + "\n"
                + "Content: " + this.content + "\n"
                + "Gen date:" + this.gen_date + "\n"
                + "read: " + this.read;
        return result;
    }
}
