package com.example.jeffliang.coen268finalproject.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jeffliang.coen268finalproject.entities.SettingItem;


public class DBOpSetting {

    private static DataBaseMgt dbm;

    public static void initDBM(DataBaseMgt dataBaseMgt) {
        dbm = dataBaseMgt;
    }

    public static boolean insertSetting(SettingItem settingItem) {
//        initDBM();
        SQLiteDatabase sqLiteDatabase = dbm.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SettingItem.COL_EMAIL, settingItem.getEmail());
        values.put(SettingItem.COL_SETTING_ITEM, settingItem.getSetting_item());
        values.put(SettingItem.COL_SETTING_VALUE, settingItem.getSetting_value());
        values.put(SettingItem.COL_LAST_UPD_DT, settingItem.getLast_upd_dt());

        boolean result = sqLiteDatabase.insert(SettingItem.TBL_NAME, null, values) == -1 ? false : true;
        sqLiteDatabase.close();
        Log.d("Jeff Debug:", "Inserted successfully: " + settingItem.getEmail() + " " + settingItem.getSetting_item() + " " + settingItem.getSetting_value());    // test only
        settingItem = searchSetting(settingItem);
        Log.d("Jeff Debug:", settingItem.toString());    // test only
        return result;
    }

    public static boolean updateSetting(SettingItem settingItem) {
        SQLiteDatabase sqLiteDatabase = dbm.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SettingItem.COL_SETTING_VALUE, settingItem.getSetting_value());
        values.put(SettingItem.COL_LAST_UPD_DT, settingItem.getLast_upd_dt());

        String[] selectArgs = new String[] {settingItem.getEmail(), settingItem.getSetting_item()};
        int rows = sqLiteDatabase.update(SettingItem.TBL_NAME, values
                , SettingItem.COL_EMAIL + " = ? AND " + SettingItem.COL_SETTING_ITEM + " = ?"
                , selectArgs);
        sqLiteDatabase.close();
        return rows > 0;
    }

    public static SettingItem searchSetting(SettingItem settingItem) {
        Log.d("Jeff Debug:", "start to search a setting item with email:" + settingItem.getEmail() + " AND item:" + settingItem.getSetting_item());    // test only
        SQLiteDatabase sqLiteDatabase = dbm.getReadableDatabase();
        String[] columns = new String[]{SettingItem.COL_SETTING_VALUE, SettingItem.COL_LAST_UPD_DT};
        String[] selectArgs = new String[]{settingItem.getEmail(), settingItem.getSetting_item()};
        Cursor cursor =  sqLiteDatabase.query(SettingItem.TBL_NAME, columns,
                SettingItem.COL_EMAIL + " = ? AND " + SettingItem.COL_SETTING_ITEM + " = ?",
                selectArgs, null, null, null, null);
        SettingItem result = new SettingItem();
        if (cursor != null && cursor.getCount() > 0) {
            Log.d("Jeff Debug:", "Setting Item record is found!");
            cursor.moveToFirst();
            result.setEmail(settingItem.getEmail());
            result.setSetting_item(settingItem.getSetting_item());
            result.setSetting_value(cursor.getString(0));
            result.setLast_upd_dt(cursor.getString(1));
            Log.d("Jeff Debug:", "cursor.getString(0): " + cursor.getString(0));
            Log.d("Jeff Debug:", "cursor.getString(1): " + cursor.getString(1));
            Log.d("Jeff Debug:", result.toString());    // test only
        }
        cursor.close();
        sqLiteDatabase.close();
        return result;
    }
}
