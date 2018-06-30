package com.example.jeffliang.coen268finalproject.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jeffliang.coen268finalproject.entities.Notification;

import java.util.ArrayList;
import java.util.List;

public class DBOpNotification {

    private static DataBaseMgt dbm;

    public static void initDBM(DataBaseMgt dataBaseMgt){
        dbm = dataBaseMgt;
    }

    public static boolean insertNotification(Notification no) {
//        initDBM();
        SQLiteDatabase sqLiteDatabase = dbm.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Notification.COL_EMAIL, no.getEmail());
        values.put(Notification.COL_TITLE, no.getTitle());
        values.put(Notification.COL_CONTENT, no.getContent());
        values.put(Notification.COL_GEN_DATE, no.getGen_date());
        values.put(Notification.COL_READ, no.getRead());

        boolean result = sqLiteDatabase.insert(Notification.TBL_NAME, null, values) == -1 ? false : true;
        sqLiteDatabase.close();
        Log.d("Jeff Debug:", no.toString());    // test only
        return result;
    }

    public static List<Notification> searchAllNotification(Notification no) {
//        initDBM();
        List<Notification> result = new ArrayList<>();

        String[] columns = new String[]{Notification.COL_EMAIL, Notification.COL_TITLE, Notification.COL_CONTENT
                                        , Notification.COL_GEN_DATE, Notification.COL_READ};
        String[] selectArgs = new String[]{no.getEmail()};

        SQLiteDatabase sqLiteDatabase = dbm.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(Notification.TBL_NAME, columns, Notification.COL_EMAIL + " = ?", selectArgs, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Notification ele = new Notification(cursor.getString(0), cursor.getString(1)
                        , cursor.getString(2), cursor.getString(3), cursor.getString(4));
                result.add(ele);
            } while(cursor.moveToNext());
        }
        return result;
    }

    public static boolean updateNotification(Notification no) {
        SQLiteDatabase sqLiteDatabase = dbm.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Notification.COL_READ, no.getRead());
        values.put(Notification.COL_LAST_UPD_DT, no.getLast_upd_dt());

        String[] selectArgs = new String[]{no.getEmail(), no.getGen_date()};

        int rows = sqLiteDatabase.update(Notification.TBL_NAME, values
                , Notification.COL_EMAIL + " = ? AND " + Notification.COL_GEN_DATE + " = ?"
                , selectArgs);
        return rows > 0;
    }


}
