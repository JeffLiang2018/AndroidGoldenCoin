package com.example.jeffliang.coen268finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.jeffliang.coen268finalproject.util.ActivitiesControl;


public class Navigation extends AppCompatActivity {

    private TableRow rowAccount;
    private TableRow rowBanking;
    private TableRow rowHistory;
    private TableRow rowNotification;
    private TableRow rowSetting;

    private Toolbar navigationToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        navigationToolbar = findViewById(R.id.navigationToolBar);
        setSupportActionBar(navigationToolbar);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setTitle("Golden Coin");
        }
//

        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);
//
        navigationToolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void clickAccountRow(View view) {
        Toast.makeText(this, "Account Row is clicked!", Toast.LENGTH_SHORT).show();    // test only
    }


    public void clickBankingRow(View view) {
//        Toast.makeText(this, "Banking Row is clicked!", Toast.LENGTH_SHORT).show();    // test only
        startActivity(new Intent(this, Banking.class));
    }

    public void clickHistoryRow(View view) {
//        Toast.makeText(this, "History Row is clicked!", Toast.LENGTH_SHORT).show();    // test only
        startActivity(new Intent(this, History.class));
    }

    public void clickNotificationRow(View view) {
//        Toast.makeText(this, "Notification Row is clicked!", Toast.LENGTH_SHORT).show();    // test only
        startActivity(new Intent(this, NotificationList.class));
    }

    public void clickSettingRow(View view) {
//        Toast.makeText(getApplicationContext(), "SettingItem Row is clicked!", Toast.LENGTH_SHORT).show();    // test only
        Intent intent = new Intent(this, Setting.class);
        startActivity(intent);
    }

    public void clickExitRow(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure to exit?");
        builder.setCancelable(false);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                ActivitiesControl.mainPage.finish();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
      protected void onPause() {
        Toast.makeText(this, "Activity Navigation is paused!", Toast.LENGTH_SHORT).show();
        super.onPause();
    }

//    public void clickButtonSetting(View view) {
//        Toast.makeText(this, "SettingItem Row is clicked!", Toast.LENGTH_SHORT).show();    // test only
//        Intent intent = new Intent(this, Setting.class);
//        startActivity(intent);
//    }
}
