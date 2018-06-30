package com.example.jeffliang.coen268finalproject;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;

import com.example.jeffliang.coen268finalproject.database.DBOpSetting;
import com.example.jeffliang.coen268finalproject.database.DataBaseMgt;
import com.example.jeffliang.coen268finalproject.entities.SettingItem;
import com.example.jeffliang.coen268finalproject.util.DateStringConvert;
import com.example.jeffliang.coen268finalproject.util.TempStorage;

import java.util.Date;

public class AutoDeposit extends AppCompatActivity {

    DataBaseMgt dbm;
    CheckBox chkToCoin;
    CheckBox chkToBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_deposit);

        dbm = new DataBaseMgt(this);

        chkToCoin = findViewById(R.id.toCoin);
        chkToBank = findViewById(R.id.toBank);

        Toolbar myToolbar = findViewById(R.id.auto_deposit_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setTitle("Auto Deposit");
        }

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBOpSetting.initDBM(dbm);
                String email = TempStorage.getAccount().getEmail();

                SettingItem siToGC = new SettingItem(email, SettingItem.ITEM_AUTO_TRAF_TO_GC, "0", DateStringConvert.dateConvertToString(new Date()));
                if (chkToCoin.isChecked()) {
                    siToGC.setSetting_value("1");
                }
                DBOpSetting.updateSetting(siToGC);

                SettingItem siToBank = new SettingItem(email, SettingItem.ITEM_AUTO_TRAF_TO_BANK, "0", DateStringConvert.dateConvertToString(new Date()));
                if (chkToBank.isChecked()) {
                    siToBank.setSetting_value("1");
                }
                DBOpSetting.updateSetting(siToBank);
                finish();
            }
        });

        DBOpSetting.initDBM(dbm);
        String email = TempStorage.getAccount().getEmail();
        SettingItem siToCoinSearch = new SettingItem(email, SettingItem.ITEM_AUTO_TRAF_TO_GC, "", "");
        SettingItem siToCoinResult = DBOpSetting.searchSetting(siToCoinSearch);
        if ("1".equals(siToCoinResult.getSetting_value())) {
            chkToCoin.setChecked(true);
        } else {
            chkToCoin.setChecked(false);
        }

        SettingItem siToBankSearch = new SettingItem(email, SettingItem.ITEM_AUTO_TRAF_TO_BANK, "", "");
        SettingItem siToBankResult = DBOpSetting.searchSetting(siToBankSearch);
        if ("1".equals(siToBankResult.getSetting_value())) {
            chkToBank.setChecked(true);
        } else {
            chkToCoin.setChecked(false);
        }
    }
}
