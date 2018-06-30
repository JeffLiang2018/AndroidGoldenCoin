package com.example.jeffliang.coen268finalproject;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableRow;

public class Setting extends AppCompatActivity {
    private TableRow security, notif;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        toolbar = findViewById(R.id.settingToolBar);
        setSupportActionBar(toolbar);

        ActionBar bar = getSupportActionBar();

        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setTitle("Setting");
        }

       // bar.setDisplayShowHomeEnabled(true);
        //bar.setDisplayHomeAsUpEnabled(true);
        //bar.setHomeButtonEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//

        security = findViewById(R.id.security);
        notif = findViewById(R.id.notif);

        //define tableRow click
        security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SecuritySetting.class));
            }
        });

        notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(), Notif_setting.class));

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
