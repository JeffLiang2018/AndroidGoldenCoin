package com.example.jeffliang.coen268finalproject;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class SecuritySetting extends AppCompatActivity {

    TextView lblAuthentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_setting);

        Toolbar myToolbar = findViewById(R.id.security_setting_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
        }

        // It will go the activity specified by parentActivityName in manifest
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        lblAuthentication = findViewById(R.id.security_setting_require);
    }

    public void clickAuthItem(View view) {
        startActivity(new Intent(this, RequireAuthentication.class));
    }
}
