package com.example.jeffliang.coen268finalproject.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jeffliang.coen268finalproject.entities.UserProfile;

public class DBOpUserProfile {

    private static DataBaseMgt dbm;

    public static boolean insertUserProfile(UserProfile up) {
//        initDBM();
        SQLiteDatabase sqLiteDatabase = dbm.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserProfile.COL_EMAIL, up.getEmail());
        values.put(UserProfile.COL_LAST_NAME, up.getSurname());
        values.put(UserProfile.COL_FIRST_NAME, up.getFirstname());
        values.put(UserProfile.COL_PHONE_NO, up.getPhoneno());
        values.put(UserProfile.COL_DOB, up.getDob());
        values.put(UserProfile.COL_ADDR_1, up.getAddr_1());
        values.put(UserProfile.COL_ADDR_2, up.getAddr_2());
        values.put(UserProfile.COL_STATE, up.getState());
        values.put(UserProfile.COL_COUNTRY, up.getCountry());
        values.put(UserProfile.COL_SSN, up.getSsn());
        values.put(UserProfile.COL_LAST_UPD_DT, up.getLast_upd_dt());

        boolean result = sqLiteDatabase.insert(UserProfile.TBL_NAME, null, values) == -1 ? false : true;
        sqLiteDatabase.close();
        return result;
    }

    public static boolean updateUserProfile(UserProfile up) {
//        initDBM();
        SQLiteDatabase sqLiteDatabase = dbm.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserProfile.COL_ADDR_1, up.getAddr_1());
        values.put(UserProfile.COL_ADDR_2, up.getAddr_2());
        values.put(UserProfile.COL_PHONE_NO, up.getPhoneno());
        values.put(UserProfile.COL_LAST_UPD_DT, up.getLast_upd_dt());

        String[] selectArgs = {up.getEmail()};
        int rows = sqLiteDatabase.update(UserProfile.TBL_NAME, values, UserProfile.COL_EMAIL + " = ?", selectArgs);
        sqLiteDatabase.close();
        return rows > 0;
    }

    public static UserProfile searchUserProfile(UserProfile up) {
//        initDBM();
        SQLiteDatabase sqLiteDatabase = dbm.getReadableDatabase();
        String[] columns = {UserProfile.COL_EMAIL, UserProfile.COL_ADDR_1, UserProfile.COL_ADDR_2
                            , UserProfile.COL_PHONE_NO, UserProfile.COL_LAST_UPD_DT};
        String[] selectArgs = {up.getEmail()};

        UserProfile result = new UserProfile();
        Cursor cursor = sqLiteDatabase.query(UserProfile.TBL_NAME, columns, UserProfile.COL_EMAIL + " = ?", selectArgs, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            result.setEmail(cursor.getString(0));
            result.setAddr_1(cursor.getString(1));
            result.setAddr_2(cursor.getString(2));
            result.setPhoneno(cursor.getString(3));
            result.setLast_upd_dt(cursor.getString(4));
        }
        sqLiteDatabase.close();
        return result;
    }

    public static void initDBM(DataBaseMgt dataBaseMgt) {
        dbm = dataBaseMgt;
    }
}
