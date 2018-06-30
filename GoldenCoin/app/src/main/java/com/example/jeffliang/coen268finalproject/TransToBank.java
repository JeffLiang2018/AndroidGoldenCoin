package com.example.jeffliang.coen268finalproject;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeffliang.coen268finalproject.database.DBOpGoldenCoinAcct;
import com.example.jeffliang.coen268finalproject.database.DBOpNotification;
import com.example.jeffliang.coen268finalproject.database.DataBaseMgt;
import com.example.jeffliang.coen268finalproject.entities.GoldenCoinAcct;
import com.example.jeffliang.coen268finalproject.entities.Notification;
import com.example.jeffliang.coen268finalproject.util.DateStringConvert;
import com.example.jeffliang.coen268finalproject.util.TempStorage;

import java.text.DecimalFormat;
import java.util.Date;

public class TransToBank extends AppCompatActivity {

    private EditText coin_to_bank;
    private TextView coin;
    private double value;
    private Button transfer, cancel,button0, button1,button2, button3, button4, button5, button6,
            button7, button8, button9, buttonDot, buttonC;

    private DBOpGoldenCoinAcct dbOpGoldenCoinAcct;
    private GoldenCoinAcct dbGoldenCoin;

    DataBaseMgt dbm;
    private double gcBalance = 0;

    DecimalFormat df = new DecimalFormat("#,###,##0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_to_bank);

        Toolbar myToolbar = findViewById(R.id.transToBank_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setTitle("Transfer To Bank");
        }

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        coin_to_bank = findViewById(R.id.coin_to_bank);
        coin = findViewById(R.id.coin);
        transfer = findViewById(R.id.transfer);
        cancel = findViewById(R.id.cancel);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        buttonDot = (Button) findViewById(R.id.buttonDot);
        buttonC = (Button) findViewById(R.id.buttonC);

        dbm = new DataBaseMgt(this);
        DBOpGoldenCoinAcct.initDBM(dbm);
        GoldenCoinAcct gcaSearch = new GoldenCoinAcct();
        gcaSearch.setEmail(TempStorage.getAccount().getEmail());
        GoldenCoinAcct gcaResult = DBOpGoldenCoinAcct.searchGoldenCoinAcct(gcaSearch);
        gcBalance = gcaResult.getBalance();
        coin.setText("$" + df.format(gcaResult.getBalance()));

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strValue = coin_to_bank.getText() + "";
                if ("0".equals(strValue)) {
                    coin_to_bank.setText("1");
                } else {
                    coin_to_bank.setText(coin_to_bank.getText() + "1");
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strValue = coin_to_bank.getText() + "";
                if ("0".equals(strValue)) {
                    coin_to_bank.setText("2");
                } else {
                    coin_to_bank.setText(coin_to_bank.getText() + "2");
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strValue = coin_to_bank.getText() + "";
                if ("0".equals(strValue)) {
                    coin_to_bank.setText("3");
                } else {
                    coin_to_bank.setText(coin_to_bank.getText() + "3");
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strValue = coin_to_bank.getText() + "";
                if ("0".equals(strValue)) {
                    coin_to_bank.setText("4");
                } else {
                    coin_to_bank.setText(coin_to_bank.getText() + "4");
                }
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strValue = coin_to_bank.getText() + "";
                if ("0".equals(strValue)) {
                    coin_to_bank.setText("5");
                } else {
                    coin_to_bank.setText(coin_to_bank.getText() + "5");
                }
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strValue = coin_to_bank.getText() + "";
                if ("0".equals(strValue)) {
                    coin_to_bank.setText("6");
                } else {
                    coin_to_bank.setText(coin_to_bank.getText() + "6");
                }
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strValue = coin_to_bank.getText() + "";
                if ("0".equals(strValue)) {
                    coin_to_bank.setText("7");
                } else {
                    coin_to_bank.setText(coin_to_bank.getText() + "7");
                }
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strValue = coin_to_bank.getText() + "";
                if ("0".equals(strValue)) {
                    coin_to_bank.setText("8");
                } else {
                    coin_to_bank.setText(coin_to_bank.getText() + "8");
                }
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strValue = coin_to_bank.getText() + "";
                if ("0".equals(strValue)) {
                    coin_to_bank.setText("9");
                } else {
                    coin_to_bank.setText(coin_to_bank.getText() + "9");
                }
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strValue = coin_to_bank.getText() + "";
                if (strValue == null || "".equals(strValue) || "0".equals(strValue)) {
                    return;
                }
                coin_to_bank.setText(coin_to_bank.getText() + "0");
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coin_to_bank.setText("");
            }
        });

        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currValue = coin_to_bank.getText() + "";
                if (currValue.indexOf('.') != -1) {
                    return;
                }
                coin_to_bank.setText(coin_to_bank.getText() + ".");
            }
        });


        //cancel button to clear the EditText content
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coin_to_bank.setText("");
                finish();
            }
        });

        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strValue = coin_to_bank.getText() + "";
                Double value = Double.valueOf(strValue);
                if (value > gcBalance) {
                    Toast.makeText(TransToBank.this, "Over your Golden Coin Balance!", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    coin_to_bank.setText("");

                    // 1. update Golden Coin Account
                    coin.setText("$" + df.format(gcBalance - value));
                    DBOpGoldenCoinAcct.initDBM(dbm);
                    GoldenCoinAcct gca = new GoldenCoinAcct();
                    gca.setEmail(TempStorage.getAccount().getEmail());
                    gca.setBalance(gcBalance - value);
                    gca.setLast_upd_dt(DateStringConvert.dateConvertToString(new Date()));
                    DBOpGoldenCoinAcct.updateGoldenCoinAcct(gca);
                    gcBalance -= value;

                    // 2.generate a notification

                    Notification no = new Notification();
                    no.setEmail(TempStorage.getAccount().getEmail());
                    no.setTitle("Transfer Money to Bank");
                    no.setContent("You transferred $" + df.format(value) + " to your bank account");
                    no.setGen_date(DateStringConvert.dateConvertToString(new Date()));
                    no.setRead("0");
                    no.setLast_upd_dt(DateStringConvert.dateConvertToString(new Date()));

                    DBOpNotification.initDBM(dbm);
                    DBOpNotification.insertNotification(no);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
