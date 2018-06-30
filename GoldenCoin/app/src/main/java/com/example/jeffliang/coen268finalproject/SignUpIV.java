package com.example.jeffliang.coen268finalproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jeffliang.coen268finalproject.util.RegistrationControl;
import com.example.jeffliang.coen268finalproject.util.TempStorage;

public class SignUpIV extends AppCompatActivity {

    TextView lblEmail;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_iv);

        lblEmail = findViewById(R.id.lblEmail);
        btnStart = findViewById(R.id.btnStart);

        String email = lblEmail.getText() + " " + TempStorage.getAccount().getEmail();
        lblEmail.setText(email);
    }

    public void clickBtnStart(View view) {
        finish();
        RegistrationControl.signup3.finish();
        RegistrationControl.signup2.finish();
        RegistrationControl.signup1.finish();
        RegistrationControl.resetRegistration();
    }
}
