package com.example.jeffliang.coen268finalproject;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jeffliang.coen268finalproject.database.DBOpBankAccount;
import com.example.jeffliang.coen268finalproject.entities.Account;
import com.example.jeffliang.coen268finalproject.entities.BankAccount;
import com.example.jeffliang.coen268finalproject.util.TempStorage;

public class AddNewCard extends AppCompatActivity {

    EditText txtNameOnCard;
    EditText txtCardNo;

    Spinner spnMonth;
    ArrayAdapter adapterMonth;

    Spinner spnYear;
    ArrayAdapter adapterYear;

    Button btnSave;
    Button btnCancel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_card);

        txtNameOnCard = findViewById(R.id.txtNameOnCard);
        txtCardNo = findViewById(R.id.txtCardNo);

        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        spnMonth = (Spinner)findViewById(R.id.spnMon);
        adapterMonth = ArrayAdapter.createFromResource(this, R.array.months, android.R.layout.simple_spinner_item);
        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMonth.setAdapter(adapterMonth);

        spnYear = (Spinner)findViewById(R.id.spnYear);
        adapterYear = ArrayAdapter.createFromResource(this, R.array.years, android.R.layout.simple_spinner_item);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnYear.setAdapter(adapterYear);

    }


    public void clickBackSpaceImage(View view) {
//        System.out.println("Return Image is clicked!");    // test only
        finish();
    }


    public void cancelClick(View view) {
//        System.out.println("Cancel Button is clicked!");    // test only
        finish();
    }

    public void saveClick(View view) {
        System.out.println("Save Button is clicked!");    // test only

        // 1. Validation
        String nameOnCard = txtNameOnCard.getText() + "";
        if (nameOnCard == null || "".equals(nameOnCard)) {
            Toast.makeText(this, "Name on Card cannot be empty!", Toast.LENGTH_LONG).show();
            txtNameOnCard.requestFocus();
            return;
        }

        String cardNo = txtCardNo.getText() + "";
        if (cardNo == null || "".equals(cardNo)) {
            Toast.makeText(this, "Card No cannot be empty!", Toast.LENGTH_LONG).show();
            txtCardNo.requestFocus();
            return;
        } else {
            if (cardNo.length() != 16) {
                Toast.makeText(this, "Invalid Card No!", Toast.LENGTH_LONG).show();
                txtCardNo.requestFocus();
                return;
            }
        }

        // 2. Save
        String expireMonth = spnMonth.getSelectedItem().toString();
        String expireYear = spnYear.getSelectedItem().toString();

        Account account = TempStorage.getAccount();
        BankAccount ba = new BankAccount(account.getEmail(), nameOnCard, cardNo, expireMonth, expireYear);
        DBOpBankAccount.insertBankAccount(ba);

        Toast.makeText(this, "Succesfully insert!", Toast.LENGTH_LONG).show();

        txtNameOnCard.setText("");
        txtCardNo.setText("");
    }
}
