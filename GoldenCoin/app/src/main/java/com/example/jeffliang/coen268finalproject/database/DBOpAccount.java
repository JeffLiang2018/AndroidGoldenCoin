package com.example.jeffliang.coen268finalproject.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.jeffliang.coen268finalproject.SignIn;
import com.example.jeffliang.coen268finalproject.entities.Account;

public class DBOpAccount{

    private static DataBaseMgt dbm;

    public static boolean insertAccount(Account account) {
//        initDBM();
        SQLiteDatabase sqLiteDatabase = dbm.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Account.COL_EMAIL, account.getEmail());
        values.put(Account.COL_PASSWORD, account.getPassword());
        values.put(Account.COL_LAST_UPD_DT, account.getLast_upd_dt());

        boolean result = sqLiteDatabase.insert(Account.TBL_NAME, null, values) == -1 ? false : true;
        sqLiteDatabase.close();
        return result;
    }

    public static boolean updateAccount(Account account) {
//        initDBM();
        SQLiteDatabase sqLiteDatabase = dbm.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Account.COL_PASSWORD, account.getPassword());
        values.put(Account.COL_LAST_UPD_DT, account.getLast_upd_dt());

        boolean result = sqLiteDatabase.update(Account.TBL_NAME, values, Account.COL_EMAIL + " = '" + account.getEmail() + "'", null) > 0 ? true : false;
        sqLiteDatabase.close();
        return result;
    }

    public static boolean deleteAccount(Account account) {
//        initDBM();
        SQLiteDatabase sqLiteDatabase = dbm.getWritableDatabase();
        int rows = sqLiteDatabase.delete(Account.TBL_NAME, Account.COL_EMAIL + " = '" + account.getEmail() + "'", null);
        sqLiteDatabase.close();
        return rows > 0;
    }

    public static Account searchAccount(Account account) {
//        initDBM();
        SQLiteDatabase sqLiteDatabase = dbm.getReadableDatabase();
        String sql = "SELECT * FROM " + Account.TBL_NAME + " WHERE " + Account.COL_EMAIL + " = '" + account.getEmail() + "'";
        System.out.println(sql);    // test only
        String[] columns = new String[]{Account.COL_EMAIL, Account.COL_PASSWORD, Account.COL_LAST_UPD_DT};
        String[] selectArgs = new String[]{account.getEmail(), account.getPassword()};

        Account result = new Account();
//        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        Cursor cursor = sqLiteDatabase.query(Account.TBL_NAME, columns
                , Account.COL_EMAIL + " = ? AND " + Account.COL_PASSWORD + " = ?"
                , selectArgs, null, null, null, null);
//
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            result.setEmail(cursor.getString(0));
            result.setPassword(cursor.getString(1));
            result.setLast_upd_dt(cursor.getString(2));
//            Toast.makeText(SignIn.class, result.getEmail() + " " + result.getPassword() + " ")
        }
        sqLiteDatabase.close();
        return result;
    }

    public static void initDBM(DataBaseMgt dataBaseMgt) {
        dbm = dataBaseMgt;
    }
}
