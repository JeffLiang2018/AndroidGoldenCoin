package com.example.jeffliang.coen268finalproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jeffliang.coen268finalproject.database.DataBaseMgt;
import com.example.jeffliang.coen268finalproject.entities.Account;
import com.example.jeffliang.coen268finalproject.entities.UserProfile;
import com.example.jeffliang.coen268finalproject.util.DateStringConvert;
import com.example.jeffliang.coen268finalproject.util.RegistrationControl;
import com.example.jeffliang.coen268finalproject.util.TempStorage;

import java.util.Date;

public class SignUpI extends AppCompatActivity {

    EditText txtEmail;
    EditText txtPassword;
    EditText txtConfirmPwd;
    EditText txtSurname;
    EditText txtFirstName;
    EditText txtPhoneNo;
    EditText txtDOB;
    EditText txtAddr1;
    EditText txtAddr2;
    Spinner spnState;
    ArrayAdapter<CharSequence> adapterState;

    Spinner spnCountry;
    ArrayAdapter<CharSequence> adapterCountry;

    DataBaseMgt dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_i);

        RegistrationControl.resetRegistration();
        RegistrationControl.signup1 = this;

        TempStorage.succNewAccountCreated = false;

        spnState = (Spinner)findViewById(R.id.spnState);
        adapterState = ArrayAdapter.createFromResource(this, R.array.state_names, android.R.layout.simple_spinner_item);
        adapterState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnState.setAdapter(adapterState);

        spnCountry = (Spinner)findViewById(R.id.spnCountry);
        adapterCountry = ArrayAdapter.createFromResource(this, R.array.country_names, android.R.layout.simple_spinner_item);
        adapterCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCountry.setAdapter(adapterCountry);

        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtConfirmPwd = findViewById(R.id.txtConfirmPassword);
        txtSurname = findViewById(R.id.txtSurName);
        txtFirstName = findViewById(R.id.txtFirstName);
        txtPhoneNo = findViewById(R.id.txtPhoneNo);
        txtDOB = findViewById(R.id.txtDOB);
        txtAddr1 = findViewById(R.id.txtAddr1);
        txtAddr2 = findViewById(R.id.txtAddr2);

        dbm = DataBaseMgt.getmInstance();

    }

    public void backClick(View view){
        finish();
    }

    public void nextClick(View view) {
        // 1. validation

        // check email
        String email = txtEmail.getText() + "";
        if (email == null || "".equals(email)) {    // Email can't be empty
            Toast.makeText(this, "Email address cannot be empty!", Toast.LENGTH_LONG).show();
            txtEmail.requestFocus();
//            txtEmail.setTextColor(Integer.parseInt("?attr/colorError"));
            return;
        } else {
            if (email.indexOf('@') == -1) {    // check correctness of email
                Toast.makeText(this, "Invalid Email address!", Toast.LENGTH_LONG).show();
                txtEmail.requestFocus();
                return;
            } else {
                // check if the email has alread exist
                String sql = " select * from account where email = '" + email + "'";
                System.out.println("sql = " + sql);    // test only
                dbm = new DataBaseMgt(this);
                Cursor cursor = dbm.searchData(sql);
                if (cursor.getCount() > 0) {
                    Toast.makeText(this, "Email address existed already!", Toast.LENGTH_LONG).show();
                    txtEmail.requestFocus();
                    return;
                }
            }
        }

        // check password
        String password = txtPassword.getText() + "";
        String confirmPwd = txtConfirmPwd.getText() + "";
        if (password == null || "".equals(password)) {
            Toast.makeText(this, "Password can't be empty!", Toast.LENGTH_LONG).show();
            txtPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmPwd)) {
            Toast.makeText(this, "Password cannot match!", Toast.LENGTH_LONG).show();
            txtConfirmPwd.requestFocus();
            return;
        }

        // check names
        String surname = txtSurname.getText() + "";
        if (surname == null || "".equals(surname)) {
            Toast.makeText(this, "Last Name cannot be empty", Toast.LENGTH_LONG).show();
            txtSurname.requestFocus();
            return;
        }

        String firstname = txtFirstName.getText() + "";
        if (firstname == null || "".equals(firstname)) {
            Toast.makeText(this, "First Name cannot be empty", Toast.LENGTH_LONG).show();
            txtFirstName.requestFocus();
        }

        // check phone no
        String phoneNo = txtPhoneNo.getText() + "";
        if (txtPhoneNo == null || "".equals(txtPhoneNo)) {
            Toast.makeText(this, "Phone No cannot be empty", Toast.LENGTH_LONG).show();
            txtPhoneNo.requestFocus();
            return;
        } else if (txtPhoneNo.length() < 10) {
            Toast.makeText(this, "Invalid Phone No", Toast.LENGTH_LONG).show();
            txtPhoneNo.requestFocus();
            return;
        }

        // check Date of Birth
        Date today = new Date();
        String dobString = txtDOB.getText() + "";
        if (dobString == null || "".equals(dobString)) {
            Toast.makeText(this, "DOB cannot be empty", Toast.LENGTH_LONG).show();
            txtDOB.requestFocus();
            return;
        } else if (dobString.charAt(2) != '/' || dobString.charAt(5) != '/' || dobString.length() != 10) {
            Toast.makeText(this, "Invalid Date of Birth", Toast.LENGTH_LONG).show();
            txtDOB.requestFocus();
            return;
        } else {
            if (today.compareTo(new Date(dobString)) <= 0) {
                Toast.makeText(this, "Invalid Date of Birth", Toast.LENGTH_LONG).show();
                txtDOB.requestFocus();
                return;
            }
        }

        // 2. save it in memory
        String str_last_upd_dt = DateStringConvert.dateConvertToString(new Date());

        Account account = TempStorage.getAccount();
        account.setEmail(email);
        account.setPassword(password);
        account.setLast_upd_dt(str_last_upd_dt);

        String addr_1 = txtAddr1.getText() + "";
        String addr_2 = txtAddr2.getText() + "";

        String state = spnState.getSelectedItem().toString();
        String country = spnCountry.getSelectedItem().toString();
        System.out.println("state = " + state);    // test only
        System.out.println("country = " + country);    // test only

        UserProfile up = TempStorage.getUserProfile();
        up.setEmail(email);
        up.setSurname(surname);
        up.setFirstname(firstname);
        up.setPhoneno(phoneNo);
        up.setDob(dobString);
        up.setAddr_1(addr_1);
        up.setAddr_2(addr_2);
        up.setState(state);
        up.setCountry(country);
        up.setLast_upd_dt(str_last_upd_dt);

        Intent intent = new Intent(SignUpI.this, SignUpII.class);
        startActivity(intent);

        if (TempStorage.succNewAccountCreated) {
            finish();
        }
    }

}
