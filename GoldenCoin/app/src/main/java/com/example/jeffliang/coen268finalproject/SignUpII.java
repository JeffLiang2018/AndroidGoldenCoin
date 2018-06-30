package com.example.jeffliang.coen268finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jeffliang.coen268finalproject.entities.BankAccount;
import com.example.jeffliang.coen268finalproject.util.RegistrationControl;
import com.example.jeffliang.coen268finalproject.util.TempStorage;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class SignUpII extends AppCompatActivity {

    EditText txtNameOnCard;
    EditText txtCardNo;

    Spinner spnMonth;
    ArrayAdapter adapterMonth;

    Spinner spnYear;
    ArrayAdapter adapterYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_ii);

        RegistrationControl.signup2 = this;

        txtNameOnCard = findViewById(R.id.txtNameOnCard);
        txtCardNo = findViewById(R.id.txtCardNo);

        spnMonth = (Spinner)findViewById(R.id.spnMon);
        adapterMonth = ArrayAdapter.createFromResource(this, R.array.months, android.R.layout.simple_spinner_item);
        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMonth.setAdapter(adapterMonth);

        spnYear = (Spinner)findViewById(R.id.spnYear);
        adapterYear = ArrayAdapter.createFromResource(this, R.array.years, android.R.layout.simple_spinner_item);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnYear.setAdapter(adapterYear);
    }

    public void backClick(View view) {
        finish();
    }

    public void skipClick(View view) {
        // jump to Registeration Page III without saving anything
        Intent intent = new Intent(this, SignUpIII.class);
        startActivity(intent);
    }

    public void nextClick(View view) {
        System.out.println("Clicked Next Button");
        // 1. validation
        // Name on the card
        String nameOnCard = txtNameOnCard.getText() + "";
        System.out.println("Name on Card: " + nameOnCard);    // test only
        if (nameOnCard == null || "".equals(nameOnCard)) {
            Toast.makeText(this, "Name On Card cannot be empty", Toast.LENGTH_LONG);
            txtNameOnCard.requestFocus();
            return;
        }

        // Card No
        String cardNumber = txtCardNo.getText() + "";
        if (cardNumber == null || "".equals(cardNumber)) {
            Toast.makeText(this, "Card No cannot be empty", Toast.LENGTH_LONG);
            txtCardNo.requestFocus();
            return;
        } else if (cardNumber.length() != 16) {
            Toast.makeText(this, "Invalid Card No", Toast.LENGTH_LONG);
            txtCardNo.requestFocus();
            return;
        }

        // 2. save
        String expireMonth = spnMonth.getSelectedItem().toString();
        String expireYear = spnYear.getSelectedItem().toString();

        BankAccount bankAccount = TempStorage.getBankAccount();
        bankAccount.setEmail(TempStorage.getUserProfile().getEmail());
        bankAccount.setCard_no(cardNumber);
        bankAccount.setName_on_card(nameOnCard);
        bankAccount.setExpire_month(expireMonth);
        bankAccount.setExpire_year(expireYear);

        // 3. jump to the next step
        Intent intent = new Intent(this, SignUpIII.class);
        startActivity(intent);

//        System.out.println("TempStorage.succNewAccountCreated = " + TempStorage.succNewAccountCreated);
        Log.d("Jeff Debug:", "TempStorage.succNewAccountCreated = " + TempStorage.succNewAccountCreated);
        if (TempStorage.succNewAccountCreated) {
            finish();
        }
    }
}
