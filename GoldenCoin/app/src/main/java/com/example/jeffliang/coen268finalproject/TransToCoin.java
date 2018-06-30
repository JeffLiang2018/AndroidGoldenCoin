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

public class TransToCoin extends AppCompatActivity {

    private EditText coin_to_gc;
    private TextView coin;
    private double value;
    private Button transfer, cancel,button0, button1,button2, button3, button4, button5, button6,
            button7, button8, button9, buttonDot, buttonC;

    private DBOpGoldenCoinAcct dbOpGoldenCoinAcct;
    private GoldenCoinAcct dbGoldenCoin;

    DataBaseMgt dbm;
    double gcBalance;
    DecimalFormat df = new DecimalFormat("#,###,##0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_to_coin);

        Toolbar myToolbar = findViewById(R.id.transToCoin_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setTitle("Transfer To Golden Coin");
        }

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dbm = new DataBaseMgt(this);
        DBOpGoldenCoinAcct.initDBM(dbm);
        GoldenCoinAcct gcaSearch = new GoldenCoinAcct();
        gcaSearch.setEmail(TempStorage.getAccount().getEmail());
        GoldenCoinAcct gcaResult = DBOpGoldenCoinAcct.searchGoldenCoinAcct(gcaSearch);
        gcBalance = gcaResult.getBalance();

        coin = findViewById(R.id.coin);
        coin_to_gc = findViewById(R.id.coin_to_gc);
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

        coin.setText("$" + df.format(gcBalance));

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strValue = coin_to_gc.getText() + "";
                if ("0".equals(strValue)) {
                    coin_to_gc.setText("1");
                } else {
                    coin_to_gc.setText(coin_to_gc.getText() + "1");
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strValue = coin_to_gc.getText() + "";
                if ("0".equals(strValue)) {
                    coin_to_gc.setText("2");
                } else {
                    coin_to_gc.setText(coin_to_gc.getText() + "2");
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strValue = coin_to_gc.getText() + "";
                if ("0".equals(strValue)) {
                    coin_to_gc.setText("3");
                } else {
                    coin_to_gc.setText(coin_to_gc.getText() + "3");
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strValue = coin_to_gc.getText() + "";
                if ("0".equals(strValue)) {
                    coin_to_gc.setText("4");
                } else {
                    coin_to_gc.setText(coin_to_gc.getText() + "4");
                }
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strValue = coin_to_gc.getText() + "";
                if ("0".equals(strValue)) {
                    coin_to_gc.setText("5");
                } else {
                    coin_to_gc.setText(coin_to_gc.getText() + "5");
                }
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strValue = coin_to_gc.getText() + "";
                if ("0".equals(strValue)) {
                    coin_to_gc.setText("6");
                } else {
                    coin_to_gc.setText(coin_to_gc.getText() + "6");
                }
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strValue = coin_to_gc.getText() + "";
                if ("0".equals(strValue)) {
                    coin_to_gc.setText("7");
                } else {
                    coin_to_gc.setText(coin_to_gc.getText() + "7");
                }
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strValue = coin_to_gc.getText() + "";
                if ("0".equals(strValue)) {
                    coin_to_gc.setText("8");
                } else {
                    coin_to_gc.setText(coin_to_gc.getText() + "8");
                }
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strValue = coin_to_gc.getText() + "";
                if ("0".equals(strValue)) {
                    coin_to_gc.setText("9");
                } else {
                    coin_to_gc.setText(coin_to_gc.getText() + "9");
                }
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strValue = coin_to_gc.getText() + "";
                if (strValue == null || "".equals(strValue) || "0".equals(strValue)) {
                    return;
                }
                coin_to_gc.setText(coin_to_gc.getText() + "0");
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coin_to_gc.setText("");
            }
        });

        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currValue = coin_to_gc.getText() + "";
                if (currValue.indexOf('.') != -1) {
                    return;
                } else if (currValue == null || "".equals(currValue)) {
                    currValue = "0";
                }
                coin_to_gc.setText(currValue + ".");
            }
        });


        //cancel button to clear the EditText content
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coin_to_gc.setText("");
                finish();
            }
        });

        transfer.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                // 1. update Golden Coin Account
                double value = Double.valueOf(coin_to_gc.getText() + "");
                coin.setText("$" + df.format(value + gcBalance));
                DBOpGoldenCoinAcct.initDBM(dbm);
                String email = TempStorage.getAccount().getEmail();
                GoldenCoinAcct gca = new GoldenCoinAcct(email, value + gcBalance);
                gca.setLast_upd_dt(DateStringConvert.dateConvertToString(new Date()));
                DBOpGoldenCoinAcct.updateGoldenCoinAcct(gca);
                coin_to_gc.setText("");

                // 2. Generate a notification
                Notification no = new Notification();
                no.setEmail(TempStorage.getAccount().getEmail());
                no.setTitle("Transfer Money to Account");
                no.setContent("You transferred $" + df.format(value) + " to your bank account");
                no.setGen_date(DateStringConvert.dateConvertToString(new Date()));
                no.setRead("0");
                no.setLast_upd_dt(DateStringConvert.dateConvertToString(new Date()));

                DBOpNotification.initDBM(dbm);
                DBOpNotification.insertNotification(no);

                Toast.makeText(TransToCoin.this, "Transfer To Bank successfully!", Toast.LENGTH_LONG).show();
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
