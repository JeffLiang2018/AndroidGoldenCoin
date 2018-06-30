package com.example.jeffliang.coen268finalproject.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jeffliang.coen268finalproject.entities.GoldenCoinAcct;

public class DBOpGoldenCoinAcct {

    private static DataBaseMgt dbm;

    private static void initDBM() {
        if (dbm == null) {
            dbm = DataBaseMgt.getmInstance();
        }
    }

    public static boolean insertGoldenCoinAcct(GoldenCoinAcct gca) {
        initDBM();
        SQLiteDatabase sqLiteDatabase = dbm.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GoldenCoinAcct.COL_EMAIL, gca.getEmail());
        values.put(GoldenCoinAcct.COL_BALANCE, gca.getBalance());
        values.put(GoldenCoinAcct.COL_LAST_UPD_DT, gca.getLast_upd_dt());

        boolean result = sqLiteDatabase.insert(GoldenCoinAcct.TBL_NAME, null, values) == -1 ? false : true;
        sqLiteDatabase.close();
        return result;
    }

    public static boolean updateGoldenCoinAcct(GoldenCoinAcct gca) {
        initDBM();
        SQLiteDatabase sqLiteDatabase = dbm.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GoldenCoinAcct.COL_BALANCE, gca.getBalance());
        values.put(GoldenCoinAcct.COL_LAST_UPD_DT, gca.getLast_upd_dt());

        String[] selectArgs = new String[] {gca.getEmail()};

        int row = sqLiteDatabase.update(GoldenCoinAcct.TBL_NAME, values, GoldenCoinAcct.COL_EMAIL + " = ?", selectArgs);
        sqLiteDatabase.close();
        return row > 0;
    }

    public static GoldenCoinAcct searchGoldenCoinAcct(GoldenCoinAcct gca) {
        initDBM();
        SQLiteDatabase sqLiteDatabase = dbm.getReadableDatabase();

        String[] columns = new String[] {GoldenCoinAcct.COL_EMAIL, GoldenCoinAcct.COL_BALANCE, GoldenCoinAcct.COL_LAST_UPD_DT};
        String[] selectArgs = new String[] {gca.getEmail()};

        GoldenCoinAcct result = new GoldenCoinAcct();
        Cursor cursor = sqLiteDatabase.query(GoldenCoinAcct.TBL_NAME, columns, GoldenCoinAcct.COL_EMAIL + " = ?", selectArgs, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            result.setEmail(cursor.getString(0));
            result.setBalance(cursor.getDouble(1));
            result.setLast_upd_dt(cursor.getString(2));
        }
        return result;
    }

    public static void initDBM(DataBaseMgt dataBaseMgt) {
        dbm = dataBaseMgt;
    }

}
