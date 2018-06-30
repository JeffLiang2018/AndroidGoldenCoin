package com.example.jeffliang.coen268finalproject;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TableRow;

public class Banking extends AppCompatActivity {

    private TableRow transTo_coin, transTo_bank, auto_deposit, link_Account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banking);

        Toolbar myToolbar = findViewById(R.id.banking_ToolBar);
        setSupportActionBar(myToolbar);

        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setTitle("Banking");
        }

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        transTo_coin = findViewById(R.id.transTo_coin);
        transTo_bank = findViewById(R.id.transTo_bank);
        auto_deposit = findViewById(R.id.auto_deposit);
        link_Account = findViewById(R.id.link_Account);

        //define the click of each row
        transTo_coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Banking.this, TransToCoin.class));
            }
        });

        transTo_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Banking.this, TransToBank.class));
            }
        });

        auto_deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Banking.this, AutoDeposit.class));
            }
        });


        //TODO: Click to link account page
        link_Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to the page of Link_account
                startActivity(new Intent(Banking.this, LinkedAccount.class));
            }
        });
    }
}
