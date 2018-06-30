package com.example.jeffliang.coen268finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jeffliang.coen268finalproject.database.DBOpBankAccount;
import com.example.jeffliang.coen268finalproject.database.DBOpSetting;
import com.example.jeffliang.coen268finalproject.database.DataBaseMgt;
import com.example.jeffliang.coen268finalproject.entities.Account;
import com.example.jeffliang.coen268finalproject.entities.BankAccount;
import com.example.jeffliang.coen268finalproject.entities.SettingItem;
import com.example.jeffliang.coen268finalproject.util.DateStringConvert;
import com.example.jeffliang.coen268finalproject.util.TempStorage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LinkedAccount extends AppCompatActivity {

    private ListView cardListView;
    private ArrayAdapter<String> bankAdapter;
    private TextView lblDefaultCardNo;
    private Button btnChange;

    private int defaultCardIndex;
    private List<BankAccount> baList;

    DataBaseMgt dbm;
    private String prefix = "ending with ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linked_account);

        lblDefaultCardNo = findViewById(R.id.lblDefaultCardNo);
        cardListView = findViewById(R.id.cardListView);

        dbm = new DataBaseMgt(this);
        // show default card
        SettingItem si = new SettingItem(TempStorage.getAccount().getEmail(), SettingItem.ITEM_DEFAULT_CARD, "", "");
        DBOpSetting.initDBM(dbm);
        SettingItem siResult = DBOpSetting.searchSetting(si);
        lblDefaultCardNo.setText(prefix + siResult.getSetting_value().substring(siResult.getSetting_value().length() - 4) + "");

        // display bank card list
        DBOpBankAccount.initDBM(dbm);
        BankAccount baSearch = new BankAccount(TempStorage.getAccount().getEmail(), "", "", "", "");
        List<BankAccount> baList = DBOpBankAccount.searchAllBankAccout(baSearch);
        List<String> baListDisp = new ArrayList<>();
        for (BankAccount ba : baList) {
            baListDisp.add(prefix + ba.getCard_no().substring(ba.getCard_no().length() - 4));
        }

        bankAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, baListDisp);
        cardListView.setAdapter(bankAdapter);
    }

    public void clickBackSpaceImage(View view) {
        finish();
    }


    public void clickChangeBankCard(View view) {
        Intent intent = new Intent(this, ChangeDefaultCard.class);
        startActivity(intent);

        DBOpSetting.initDBM(dbm);
        SettingItem si = DBOpSetting.searchSetting(new SettingItem(TempStorage.getAccount().getEmail(), SettingItem.ITEM_DEFAULT_CARD, "", ""));
        lblDefaultCardNo.setText("ending with " + si.getSetting_value().substring(si.getSetting_value().length() - 4));

    }

    public void clickAddNewCard(View view) {
        Intent intent = new Intent(this, AddNewCard.class);
        startActivity(intent);

        DBOpBankAccount.initDBM(dbm);
        BankAccount baSearch = new BankAccount(TempStorage.getAccount().getEmail(), "", "", "", "");
        List<BankAccount> baList = DBOpBankAccount.searchAllBankAccout(baSearch);
        List<String> baListDisp = new ArrayList<>();
        for (BankAccount ba : baList) {
            baListDisp.add(prefix + ba.getCard_no().substring(ba.getCard_no().length() - 4));
        }

        bankAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, baListDisp);
        cardListView.setAdapter(bankAdapter);
    }
}
