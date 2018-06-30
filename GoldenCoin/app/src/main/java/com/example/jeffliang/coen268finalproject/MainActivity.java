package com.example.jeffliang.coen268finalproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jeffliang.coen268finalproject.database.DataBaseMgt;


public class MainActivity extends AppCompatActivity {

    Button btnSignIn;
    Button btnSignUp;
    DataBaseMgt dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSignIn = findViewById(R.id.btnToSignIn);
        btnSignUp = findViewById(R.id.btnToSignUp);

        SignInClick(this);
        toSignUpPage(this);

        dbm = new DataBaseMgt(this);
    }

    private void SignInClick(final Activity activity) {
        btnSignIn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignIn.class);

                startActivity(intent);
            }
        });
    }

    private void toSignUpPage(final Activity activity) {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUpI.class);
                startActivity(intent);
            }
        });
    }
}
