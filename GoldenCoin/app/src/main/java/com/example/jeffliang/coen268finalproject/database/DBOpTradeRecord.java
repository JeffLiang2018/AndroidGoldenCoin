package com.example.jeffliang.coen268finalproject.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jeffliang.coen268finalproject.entities.TradeRecord;

import java.util.ArrayList;
import java.util.List;

public class DBOpTradeRecord {

    private static DataBaseMgt dbm;

    public static void initDBM(DataBaseMgt dataBaseMgt) {
        dbm = dataBaseMgt;
    }


    public static boolean insertTradeRecord(TradeRecord tr) {
//        initDBM();
        SQLiteDatabase sqLiteDatabase = dbm.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TradeRecord.COL_EMAIL, tr.getEmail());
        values.put(TradeRecord.COL_COIN_NAME, tr.getCoin_name());
        values.put(TradeRecord.COL_COIN_ACRONYM, tr.getCoin_acronym());
        values.put(TradeRecord.COL_BUY_SELL_FLAG, tr.getBuy_sell_flag());
        values.put(TradeRecord.COL_UNIT_PRICE, tr.getUnit_price());
        values.put(TradeRecord.COL_COIN_AMOUNT, tr.getAmount());
        values.put(TradeRecord.COL_MONEY_AMOUNT, tr.getMoney_account());
        values.put(TradeRecord.COL_TRAN_DATE, tr.getTran_date());


        boolean result = sqLiteDatabase.insert(TradeRecord.TBL_NAME, null, values) == -1 ? false : true;
        sqLiteDatabase.close();
        return result;
    }


    public static List<TradeRecord> searchAllTradeRecord(TradeRecord tr) {
//        initDBM();
        SQLiteDatabase sqLiteDatabase = dbm.getReadableDatabase();

        String[] columns = new String[]{TradeRecord.COL_EMAIL, TradeRecord.COL_COIN_NAME, TradeRecord.COL_COIN_ACRONYM
                                    , TradeRecord.COL_BUY_SELL_FLAG, TradeRecord.COL_UNIT_PRICE, TradeRecord.COL_COIN_AMOUNT
                                    , TradeRecord.COL_MONEY_AMOUNT, TradeRecord.COL_TRAN_DATE};
        String[] selectArgs = new String[] {tr.getEmail()};

        List<TradeRecord> result = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(TradeRecord.TBL_NAME, columns, TradeRecord.COL_EMAIL + " = ?", selectArgs, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                TradeRecord element = new TradeRecord(cursor.getString(0), cursor.getString(1), cursor.getString(2)
                                                    , cursor.getInt(3), cursor.getDouble(4), cursor.getDouble(5)
                                                    , cursor.getDouble(6), cursor.getString(7));
                result.add(element);
            } while (cursor.moveToFirst());
        }

        return result;
    }

    public static Cursor searchAllTradeRecordCursor(TradeRecord tr) {
        SQLiteDatabase sqLiteDatabase = dbm.getReadableDatabase();

        String[] columns = new String[]{
                "_rowid_ as _id",
                TradeRecord.COL_EMAIL,
                TradeRecord.COL_COIN_NAME,
                TradeRecord.COL_COIN_ACRONYM,
                TradeRecord.COL_BUY_SELL_FLAG,
                TradeRecord.COL_UNIT_PRICE,
                TradeRecord.COL_COIN_AMOUNT,
                TradeRecord.COL_MONEY_AMOUNT,
                TradeRecord.COL_TRAN_DATE};
        String[] selectArgs = new String[]{tr.getEmail()};

        Cursor cursor = sqLiteDatabase.query(TradeRecord.TBL_NAME, columns, TradeRecord.COL_EMAIL + " = ?", selectArgs, null, null, TradeRecord.COL_TRAN_DATE + " DESC", null);
        return cursor;
    }

}
