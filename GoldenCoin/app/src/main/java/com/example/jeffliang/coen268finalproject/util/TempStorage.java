package com.example.jeffliang.coen268finalproject.util;

import com.example.jeffliang.coen268finalproject.database.DataBaseMgt;
import com.example.jeffliang.coen268finalproject.entities.Account;
import com.example.jeffliang.coen268finalproject.entities.BankAccount;
import com.example.jeffliang.coen268finalproject.entities.UserProfile;

public class TempStorage {

    private static TempStorage instance;

    private static Account account;
    private static UserProfile userProfile;
    private static BankAccount bankAccount;

    public static boolean succNewAccountCreated;

    private static DataBaseMgt dbm;


    public TempStorage getInstance() {
        if (instance == null) {
            instance = new TempStorage();
        }
        return instance;
    }


    public static UserProfile getUserProfile() {
        if (userProfile == null) {
            userProfile = new UserProfile();
        }
        return userProfile;
    }

    public static void setUserProfile(UserProfile userProfile) {
        TempStorage.userProfile = userProfile;
    }

    public static BankAccount getBankAccount() {
        if (bankAccount == null) {
            bankAccount = new BankAccount();
        }
        return bankAccount;
    }

    public static void setBankAccount(BankAccount bankAccount) {
        TempStorage.bankAccount = bankAccount;
    }

    public static Account getAccount() {
        if (account == null) {
            account = new Account();
        }
        return account;
    }

    public static void setAccount(Account account) {
        TempStorage.account = account;
    }
}
