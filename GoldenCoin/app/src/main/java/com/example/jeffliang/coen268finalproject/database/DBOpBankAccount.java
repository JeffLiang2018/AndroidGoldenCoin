package com.example.jeffliang.coen268finalproject.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jeffliang.coen268finalproject.entities.BankAccount;

import java.util.ArrayList;
import java.util.List;

public class DBOpBankAccount {

    private static DataBaseMgt dbm;

    public static boolean insertBankAccount(BankAccount ba) {
//        initDBM();
        SQLiteDatabase sqLiteDatabase = dbm.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BankAccount.COL_EMAIL, ba.getEmail());
        values.put(BankAccount.COL_NAME_ON_CARD, ba.getName_on_card());
        values.put(BankAccount.COL_CARD_NO, ba.getCard_no());
        values.put(BankAccount.COL_EXPIRE_MONTH, ba.getExpire_month());
        values.put(BankAccount.COL_EXPIRE_YEAR, ba.getExpire_year());
        values.put(BankAccount.COL_LAST_UPD_DT, ba.getLast_upd_dt());

        boolean result = sqLiteDatabase.insert(BankAccount.TBL_NAME, null, values) == -1 ? false : true;
        sqLiteDatabase.close();
        return result;
    }

    public static BankAccount searchBankAccount(BankAccount ba) {
//        initDBM();
        SQLiteDatabase sqLiteDatabase = dbm.getReadableDatabase();
        String[] columns = {BankAccount.COL_EMAIL, BankAccount.COL_NAME_ON_CARD, BankAccount.COL_CARD_NO
                            , BankAccount.COL_EXPIRE_MONTH, BankAccount.COL_EXPIRE_YEAR, BankAccount.COL_LAST_UPD_DT};
        String[] selectArgs = {ba.getEmail(), ba.getCard_no()};
        Cursor cursor = sqLiteDatabase.query(BankAccount.TBL_NAME, columns
                                            , BankAccount.COL_EMAIL + " = ? AND " + BankAccount.COL_CARD_NO + " = ? "
                                            , selectArgs, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        BankAccount result = new BankAccount();
        result.setEmail(cursor.getString(0));
        result.setName_on_card(cursor.getString(1));
        result.setCard_no(cursor.getString(2));
        result.setExpire_month(cursor.getString(3));
        result.setExpire_year(cursor.getString(4));
        result.setLast_upd_dt(cursor.getString(5));

        sqLiteDatabase.close();

        return result;
    }

    public static List<BankAccount> searchAllBankAccout(BankAccount ba) {
//        initDBM();
        SQLiteDatabase sqLiteDatabase = dbm.getReadableDatabase();
        List<BankAccount> results = new ArrayList<>();
        String[] columns = {BankAccount.COL_EMAIL, BankAccount.COL_NAME_ON_CARD, BankAccount.COL_CARD_NO
                , BankAccount.COL_EXPIRE_MONTH, BankAccount.COL_EXPIRE_YEAR, BankAccount.COL_LAST_UPD_DT};
        String[] selectArgs = {ba.getEmail()};
        Cursor cursor = sqLiteDatabase.query(BankAccount.TBL_NAME, columns
                                            , BankAccount.COL_EMAIL + " = ?"
                                            , selectArgs, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                BankAccount bankAccount = new BankAccount();
                bankAccount.setEmail(cursor.getString(0));
                bankAccount.setName_on_card(cursor.getString(1));
                bankAccount.setCard_no(cursor.getString(2));
                bankAccount.setExpire_month(cursor.getString(3));
                bankAccount.setExpire_year(cursor.getString(4));
                bankAccount.setLast_upd_dt(cursor.getString(5));

                Log.d("Jeff Debug: ", "search All Bank Account: " + bankAccount.toString());    // test only
                results.add(bankAccount);
            } while(cursor.moveToNext());
        }

        sqLiteDatabase.close();

        return results;
    }


    public static Cursor searchAllBankAccoutCursor(BankAccount ba) {

        SQLiteDatabase sqLiteDatabase = dbm.getReadableDatabase();

        String[] columns = {
                "_rowid_ as _id",
                BankAccount.COL_EMAIL, BankAccount.COL_NAME_ON_CARD, BankAccount.COL_CARD_NO
                , BankAccount.COL_EXPIRE_MONTH, BankAccount.COL_EXPIRE_YEAR, BankAccount.COL_LAST_UPD_DT};
        String[] selectArgs = {ba.getEmail()};
        Cursor cursor = sqLiteDatabase.query(BankAccount.TBL_NAME, columns
                , BankAccount.COL_EMAIL + " = ?"
                , selectArgs, null, null, null, null);

//        sqLiteDatabase.close();

        return cursor;
    }

    public static void initDBM(DataBaseMgt dataBaseMgt) {
        dbm = dataBaseMgt;
    }
}
