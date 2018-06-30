package com.example.jeffliang.coen268finalproject;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.jeffliang.coen268finalproject.database.DBOpAccount;
import com.example.jeffliang.coen268finalproject.database.DBOpSetting;
import com.example.jeffliang.coen268finalproject.database.DataBaseMgt;
import com.example.jeffliang.coen268finalproject.entities.Account;
import com.example.jeffliang.coen268finalproject.entities.SettingItem;
import com.example.jeffliang.coen268finalproject.util.DateStringConvert;
import com.example.jeffliang.coen268finalproject.util.TempStorage;

import java.util.Date;

public class SignIn extends AppCompatActivity {

//    Button btnMainPage;
//    Button btnLinkedAccount;
//    Button btnAddNewCard;

    EditText txtEmail;
    EditText txtPassword;
    Switch optRemEmail;
    Button btnSignIn;
    boolean remEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Toolbar myToolbar = findViewById(R.id.sign_in_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar bar = getSupportActionBar();
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle("Golden Coin");

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("aaaaaaaaa");    // test only
                finish();
            }
        });

        txtEmail = findViewById(R.id.sign_in_page_et_email);
        txtPassword = findViewById(R.id.sign_in_page_et_password);
        btnSignIn = findViewById(R.id.sign_in_page_btn_log_in);
        optRemEmail = (Switch)findViewById(R.id.sign_in_page_switch);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. validation

                System.out.println("Start validation");    // test only
                String email = txtEmail.getText() + "";
                if (email == null || "".equals(email)) {
                    Toast.makeText(SignIn.this, "Please input email", Toast.LENGTH_LONG).show();
                    txtEmail.requestFocus();
                    return;
                }

                String password = txtPassword.getText() + "";
                if (password == null || "".equals(password)) {
                    Toast.makeText(SignIn.this, "Please input password", Toast.LENGTH_LONG).show();
                    txtPassword.requestFocus();
                    return;
                }

                // 2. log in
                Account account = new Account(email, password);
//                System.out.println(account.getEmail() + "   " + account.getPassword());
                System.out.println(email + "  " + password);    // test only
                DataBaseMgt dbm = new DataBaseMgt(SignIn.this);
                DBOpAccount.initDBM(dbm);
                Account account2 = DBOpAccount.searchAccount(account);
                if (account2.getEmail() == null || "".equals(account2.getEmail()) || account2.getPassword() == null || "".equals(account2.getPassword())) {
                    Toast.makeText(SignIn.this, "Incorrect email or password", Toast.LENGTH_LONG).show();
                    txtEmail.requestFocus();
                    return;
                }

                System.out.println("Email and Password are correct!");    // test only
                DBOpSetting.initDBM(dbm);
                if (remEmail) {
                    SettingItem settingItem = new SettingItem(email, SettingItem.ITEM_REMEMBER_EMAIL, "1", DateStringConvert.dateConvertToString(new Date()));
                    DBOpSetting.updateSetting(settingItem);
                } else {
                    SettingItem settingItem = new SettingItem(email, SettingItem.ITEM_REMEMBER_EMAIL, "0", DateStringConvert.dateConvertToString(new Date()));
                    DBOpSetting.updateSetting(settingItem);
                }

                TempStorage.setAccount(account);
                Log.d("Jeff Debug", "Log in complete, jump to another page now");    // test only
//
                // 3. jump to main page

                Intent intent = new Intent(SignIn.this, MainPage.class);

                startActivity(intent);

                finish();
            }
        });

        optRemEmail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                remEmail = isChecked;
            }
        });

    }

//    public void clickLogIn(View view) {
//        // 1. validation
//        System.out.println("Start validation");    // test only
//        String email = txtEmail.getText() + "";
//        if (email == null || "".equals(email)) {
//            Toast.makeText(this, "Please input email", Toast.LENGTH_LONG).show();
//            txtEmail.requestFocus();
//            return;
//        }
//
//        String password = txtPassword.getText() + "";
//        if (password == null || "".equals(password)) {
//            Toast.makeText(this, "Please input password", Toast.LENGTH_LONG).show();
//            txtPassword.requestFocus();
//            return;
//        }
//
//        // 2. log in
////                Account account = new Account(email, password);
//////                System.out.println(account.getEmail() + "   " + account.getPassword());
////                System.out.println(email + "  " + password);    // test only
////                DataBaseMgt dbm = new DataBaseMgt(SignIn.this);
////                DBOpAccount.initDBM(dbm);
////                Account account2 = DBOpAccount.searchAccount(account);
////                if (!(account2.getEmail() == null || "".equals(account2.getEmail())) && !(account2.getPassword() == null || "".equals(account2.getPassword()))) {
////                    Toast.makeText(SignIn.this, "Incorrect email or password", Toast.LENGTH_LONG).show();
////                    txtEmail.requestFocus();
////                    return;
////                }
//
//        DataBaseMgt dbm = new DataBaseMgt(this);
////                String sql = " select * from account where email = '" + email + "'";
//        String sql = "SELECT * FROM " + Account.TBL_NAME + " WHERE " + Account.COL_EMAIL + " = '" + email + "' AND " + Account.COL_PASSWORD + " = '" + password + "'";
//        Cursor cursor = dbm.searchData(sql);
//        if (cursor.getCount() > 0) {
////                    Toast.makeText(SignIn.this, "Email address existed already!", Toast.LENGTH_SHORT).show();
////                    System.out.println("Email address is found");
////                    txtEmail.requestFocus();
////                    return;
//        } else {
//            Toast.makeText(this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
//            txtEmail.requestFocus();
//            return;
//        }
//
//        System.out.println("Email and Password are correct!");    // test only
////                DBOpSetting.initDBM(dbm);
////                if (remEmail) {
////                    SettingItem setting = new SettingItem(email, SettingItem.ITEM_DEFAULT_CARD, "1", DateStringConvert.dateConvertToString(new Date()));
////                    DBOpSetting.updateSetting(setting);
////                } else {
////                    SettingItem setting = new SettingItem(email, SettingItem.ITEM_DEFAULT_CARD, "0", DateStringConvert.dateConvertToString(new Date()));
////                    DBOpSetting.updateSetting(setting);
////                }
////
//        // 3. jump to main page
//        Intent intent = new Intent(this, MainPage.class);
//        startActivity(intent);
//    }

}
