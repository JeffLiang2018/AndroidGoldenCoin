package com.example.jeffliang.coen268finalproject.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jeffliang.coen268finalproject.entities.Account;
import com.example.jeffliang.coen268finalproject.entities.BankAccount;
import com.example.jeffliang.coen268finalproject.entities.GoldenCoinAcct;
import com.example.jeffliang.coen268finalproject.entities.Notification;
import com.example.jeffliang.coen268finalproject.entities.SettingItem;
import com.example.jeffliang.coen268finalproject.entities.TradeRecord;
import com.example.jeffliang.coen268finalproject.entities.UserProfile;

public class DataBaseMgt extends SQLiteOpenHelper {

    private static DataBaseMgt mInstance;
    private static final int DATABASE_VERSION = 1;
    private static final String DB_NAME = "GoldenGoin";
    private static Context context;

    public DataBaseMgt(Context context){
        super(context, DB_NAME, null, DATABASE_VERSION);
        this.context = context;
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        System.out.println("DataBastMgt onCreate is called again!");    // test only
        // create table account
        String createTBL = "CREATE TABLE " + Account.TBL_NAME +
                " (" + Account.COL_EMAIL + " TEXT, "
                     + Account.COL_PASSWORD + " TEXT, "
                     + Account.COL_LAST_UPD_DT + " TEXT);";
        System.out.println(createTBL);
        sqLiteDatabase.execSQL(createTBL);

        // create table user_profile
        createTBL = "CREATE TABLE " + UserProfile.TBL_NAME +
                " ( " + UserProfile.COL_EMAIL +  " TEXT, "
                      + UserProfile.COL_LAST_NAME +  " TEXT, "
                      + UserProfile.COL_FIRST_NAME + " TEXT, "
                      + UserProfile.COL_PHONE_NO + " TEXT, "
                      + UserProfile.COL_DOB + " TEXT, "
                      + UserProfile.COL_ADDR_1 + " TEXT, "
                      + UserProfile.COL_ADDR_2 + " TEXT, "
                      + UserProfile.COL_STATE + " TEXT, "
                      + UserProfile.COL_COUNTRY + " TEXT, "
                      + UserProfile.COL_SSN + " TEXT, "
                      + UserProfile.COL_LAST_UPD_DT + " TEXT)";
        System.out.println(createTBL);
        sqLiteDatabase.execSQL(createTBL);

        //create table bank_account
        createTBL = "CREATE TABLE " + BankAccount.TBL_NAME +
                " (" + BankAccount.COL_EMAIL + " TEXT, "
                     + BankAccount.COL_NAME_ON_CARD + " TEXT, "
                     + BankAccount.COL_CARD_NO + " TEXT, "
                     + BankAccount.COL_EXPIRE_MONTH + " TEXT, "
                     + BankAccount.COL_EXPIRE_YEAR + " TEXT, "
                     + BankAccount.COL_LAST_UPD_DT + " TEXT)";
        System.out.println(createTBL);
        sqLiteDatabase.execSQL(createTBL);

        // create table notification
        createTBL = "CREATE TABLE " + Notification.TBL_NAME +
                " (" + Notification.COL_EMAIL + " TEXT, "
                     + Notification.COL_TITLE + " TEXT, "
                     + Notification.COL_CONTENT + " TEXT, "
                     + Notification.COL_GEN_DATE + " TEXT, "
                     + Notification.COL_READ + " TEXT, "
                     + Notification.COL_LAST_UPD_DT + " TEXT) ";
        System.out.println(createTBL);    // test only
        sqLiteDatabase.execSQL(createTBL);

        // create table golden_coin_acct
        createTBL = "CREATE TABLE " + GoldenCoinAcct.TBL_NAME +
                " (" + GoldenCoinAcct.COL_EMAIL + " TEXT, "
                + GoldenCoinAcct.COL_BALANCE + " NUMERIC, "
                + GoldenCoinAcct.COL_LAST_UPD_DT + " TEXT)";
        System.out.println(createTBL);    // test only
        sqLiteDatabase.execSQL(createTBL);

        // create table transaction
        createTBL = "CREATE TABLE " + TradeRecord.TBL_NAME +
                " ( " + TradeRecord.COL_EMAIL + " TEXT, "
                      + TradeRecord.COL_COIN_NAME + " TEXT, "
                      + TradeRecord.COL_COIN_ACRONYM + " TEXT, "
                      + TradeRecord.COL_BUY_SELL_FLAG + " INTEGER, "
                      + TradeRecord.COL_UNIT_PRICE + " NUMERIC, "
                      + TradeRecord.COL_COIN_AMOUNT + " NUMERIC, "
                      + TradeRecord.COL_MONEY_AMOUNT + " NUMERIC, "
                      + TradeRecord.COL_TRAN_DATE + " TEXT)";
        System.out.println(createTBL);    // test only
        sqLiteDatabase.execSQL(createTBL);

        // create table setting
        createTBL = "CREATE TABLE " + SettingItem.TBL_NAME +
                " ( " + SettingItem.COL_EMAIL + " TEXT, "
                      + SettingItem.COL_SETTING_ITEM + " TEXT, "
                      + SettingItem.COL_SETTING_VALUE + " TEXT, "
                      + SettingItem.COL_LAST_UPD_DT + " TEXT)";
        System.out.println(createTBL);    // test only
        sqLiteDatabase.execSQL(createTBL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sqlPrefix = "DROP TABLE IF EXISTS ";
        sqLiteDatabase.execSQL(sqlPrefix + Account.TBL_NAME);
        sqLiteDatabase.execSQL(sqlPrefix + UserProfile.TBL_NAME);
        sqLiteDatabase.execSQL(sqlPrefix + BankAccount.TBL_NAME);
        sqLiteDatabase.execSQL(sqlPrefix + Notification.TBL_NAME);
        sqLiteDatabase.execSQL(sqlPrefix + GoldenCoinAcct.TBL_NAME);
        sqLiteDatabase.execSQL(sqlPrefix + TradeRecord.TBL_NAME);
        sqLiteDatabase.execSQL(sqlPrefix + SettingItem.TBL_NAME);

        onCreate(sqLiteDatabase);
    }

    public DataBaseMgt getInstance() {
        return this;
    }



    public boolean insertData(String sql) {
        boolean success = true;
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            sqLiteDatabase.execSQL(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
            success = false;
        }
        return success;
    }

    public boolean updateData(String sql) {
        boolean success = true;
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            sqLiteDatabase.execSQL(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
            success = false;
        }
        return success;
    }

    public boolean deleteData(String sql) {
        boolean success = true;
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            sqLiteDatabase.execSQL(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
            success = false;
        }
        return success;
    }

    public Cursor searchData(String sql) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        return cursor;
    }

    public static DataBaseMgt getmInstance() {
        if (mInstance == null) {
            System.out.println("DatabasaeMgt Object is null!!!");
//            mInstance = new DataBaseMgt(context);
        }
        return mInstance;
    }

}
