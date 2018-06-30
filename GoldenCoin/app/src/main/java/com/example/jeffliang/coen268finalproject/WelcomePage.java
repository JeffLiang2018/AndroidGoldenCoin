package com.example.jeffliang.coen268finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.jeffliang.coen268finalproject.database.DataBaseMgt;

public class WelcomePage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        new DataBaseMgt(this);
    }

    public void signIn(View view) {
        // Sign In clicked
        Intent sign_in_intent = new Intent();
        sign_in_intent.setClass(WelcomePage.this, SignIn.class);
        startActivity(sign_in_intent);
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpI.class);
        startActivity(intent);
    }
}
