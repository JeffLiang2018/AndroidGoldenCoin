package com.example.jeffliang.coen268finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jeffliang.coen268finalproject.database.DBOpAccount;
import com.example.jeffliang.coen268finalproject.database.DBOpBankAccount;
import com.example.jeffliang.coen268finalproject.database.DBOpGoldenCoinAcct;
import com.example.jeffliang.coen268finalproject.database.DBOpNotification;
import com.example.jeffliang.coen268finalproject.database.DBOpSetting;
import com.example.jeffliang.coen268finalproject.database.DBOpUserProfile;
import com.example.jeffliang.coen268finalproject.database.DataBaseMgt;
import com.example.jeffliang.coen268finalproject.entities.Account;
import com.example.jeffliang.coen268finalproject.entities.BankAccount;
import com.example.jeffliang.coen268finalproject.entities.GoldenCoinAcct;
import com.example.jeffliang.coen268finalproject.entities.Notification;
import com.example.jeffliang.coen268finalproject.entities.SettingItem;
import com.example.jeffliang.coen268finalproject.entities.UserProfile;
import com.example.jeffliang.coen268finalproject.util.DateStringConvert;
import com.example.jeffliang.coen268finalproject.util.RegistrationControl;
import com.example.jeffliang.coen268finalproject.util.TempStorage;

import java.util.Date;

public class SignUpIII extends AppCompatActivity {

    EditText txtDOB;
    EditText txtSurame;
    EditText txtFirstName;
    EditText txtSSN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_iii);

        RegistrationControl.signup3 = this;

        txtDOB = findViewById(R.id.txtDOB);
        txtSurame = findViewById(R.id.txtSurName);
        txtFirstName = findViewById(R.id.txtFirstName);
        txtSSN = findViewById(R.id.txtSSN);
    }

    public void backClick(View view) {
        finish();

    }

    public void nextClick(View view) {
        // 1. validation
        String dobString = txtDOB.getText() + "";
        if (dobString == null || "".equals(dobString)) {
            Toast.makeText(this, "DOB cannot be empty!", Toast.LENGTH_LONG);
            txtDOB.requestFocus();
            return;
        } else {
            Date today = new Date();
            if (today.compareTo(new Date(dobString)) <= 0) {
                Toast.makeText(this, "Invalid DOB!", Toast.LENGTH_LONG);
                txtDOB.requestFocus();
                return;
            }
        }

        String surname = txtSurame.getText() + "";
        if (surname == null || "".equals(surname)) {
            Toast.makeText(this, "Last Name cannot be empty", Toast.LENGTH_SHORT).show();
            txtSurame.requestFocus();
            return;
        }

        String firstname = txtFirstName.getText() + "";
        if (firstname == null || "".equals(firstname)) {
            Toast.makeText(this, "First Name cannot be empty", Toast.LENGTH_LONG).show();
            txtFirstName.requestFocus();
            return;
        }

        String ssn = txtSSN.getText() + "";
        if (ssn == null || "".equals(ssn)) {
            Toast.makeText(this, "SSN cannot be empty", Toast.LENGTH_LONG).show();
            txtSSN.requestFocus();
            return;
        } else {
            if (ssn.length() != 9) {
                Toast.makeText(this, "Invalid SSN", Toast.LENGTH_LONG).show();
                txtSSN.requestFocus();
                return;

            }
        }

        // 2. save data to memory
        DataBaseMgt dbm = new DataBaseMgt(this);

        String last_upd_dt = DateStringConvert.dateConvertToString(new Date());
        // Save Account to SQLite
        Account acct = TempStorage.getAccount();
        acct.setLast_upd_dt(last_upd_dt);
        DBOpAccount.initDBM(dbm);
        DBOpAccount.insertAccount(acct);
        System.out.println(acct.toString());    // test only

        // Save User Profile to SQLite
        UserProfile up = TempStorage.getUserProfile();
        up.setDob(dobString);
        up.setSurname(surname);
        up.setFirstname(firstname);
        up.setSsn(ssn);
        up.setLast_upd_dt(last_upd_dt);
        DBOpUserProfile.initDBM(dbm);
        DBOpUserProfile.insertUserProfile(up);
        System.out.println(up.toString());    // test only

        // Create a Golden Coin Account
        GoldenCoinAcct gca = new GoldenCoinAcct(acct.getEmail(), 0);
        gca.setLast_upd_dt(last_upd_dt);
        DBOpGoldenCoinAcct.initDBM(dbm);
        DBOpGoldenCoinAcct.insertGoldenCoinAcct(gca);

        // Save the mandatory setting records:
        // AUTHENTICATION, REMEMBER_EMAIL, REMEMBER_PASSWORD, AUTO_TRAF_TO_GC, AUTO_TRAF_TO_BANK
        DBOpSetting.initDBM(dbm);
        SettingItem authentication = new SettingItem(acct.getEmail(), SettingItem.ITEM_AUTHENTICATION, "1", last_upd_dt);
        DBOpSetting.insertSetting(authentication);
        SettingItem remember_pwd = new SettingItem(acct.getEmail(), SettingItem.ITEM_REMEMBER_PASSWORD, "0", last_upd_dt);
        DBOpSetting.insertSetting(remember_pwd);
        SettingItem auto_tran_to_gc = new SettingItem(acct.getEmail(), SettingItem.ITEM_AUTO_TRAF_TO_GC, "0", last_upd_dt);
        DBOpSetting.insertSetting(auto_tran_to_gc);
        SettingItem auto_tran_to_bank = new SettingItem(acct.getEmail(), SettingItem.ITEM_AUTO_TRAF_TO_BANK, "0", last_upd_dt);
        DBOpSetting.insertSetting(auto_tran_to_bank);

        // Save Bank Account if exists
        BankAccount ba = TempStorage.getBankAccount();
        if (!(ba.getEmail() == null || "".equals(ba.getEmail())) &&
                !(ba.getCard_no() == null || "".equals(ba.getCard_no())) &&
                !(ba.getName_on_card() == null || "".equals(ba.getName_on_card()))) {
            ba.setLast_upd_dt(last_upd_dt);
            DBOpBankAccount.initDBM(dbm);
            DBOpBankAccount.insertBankAccount(ba);
//            System.out.println(ba.toString());    // test only
            Log.d("Jeff Debug:", ba.toString());    // test only

            // Set Default Bank Card
            SettingItem default_card = new SettingItem(acct.getEmail(), SettingItem.ITEM_DEFAULT_CARD, ba.getCard_no(), last_upd_dt);
            Log.d("Jeff Debug:", "Default Card No:" + ba.getCard_no());    // test only
            DBOpSetting.initDBM(dbm);
            DBOpSetting.insertSetting(default_card);
            Log.d("Jeff Debug:", "successfully inserted default card!");    // test only
        }

        Notification notification = new Notification();
        notification.setEmail(acct.getEmail());
        notification.setTitle("New Account Created");
        notification.setContent("Congradulation! You have your own GoldenCoin Account now.");
        notification.setGen_date(DateStringConvert.dateConvertToString(new Date()));
        notification.setRead("0");
        notification.setLast_upd_dt(notification.getGen_date());
        DBOpNotification.initDBM(dbm);
        DBOpNotification.insertNotification(notification);

        TempStorage.succNewAccountCreated = true;
        // 3. jump to the next page
        Intent intent = new Intent(this, SignUpIV.class);
        startActivity(intent);

        finish();
    }
}
