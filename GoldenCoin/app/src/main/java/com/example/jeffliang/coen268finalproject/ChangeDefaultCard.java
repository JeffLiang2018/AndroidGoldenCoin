package com.example.jeffliang.coen268finalproject;

import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SimpleCursorAdapter;

import com.example.jeffliang.coen268finalproject.database.DBOpBankAccount;
import com.example.jeffliang.coen268finalproject.database.DBOpSetting;
import com.example.jeffliang.coen268finalproject.database.DataBaseMgt;
import com.example.jeffliang.coen268finalproject.entities.BankAccount;
import com.example.jeffliang.coen268finalproject.entities.SettingItem;
import com.example.jeffliang.coen268finalproject.util.DateStringConvert;
import com.example.jeffliang.coen268finalproject.util.TempStorage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

public class ChangeDefaultCard extends AppCompatActivity {

    private ListView lv_cards;
    private DataBaseMgt mDbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_default_card);

        Toolbar myToolbar = findViewById(R.id.default_card_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
        }

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Save some test accounts into the database
//        List<BankAccount> bk_list = new ArrayList<>();
//        bk_list.add(new BankAccount("bob@gmail.com", "Bob", "4392100020001111", "12", "2019"));
//        bk_list.add(new BankAccount("abc@google.com", "ABC", "4392100020002222", "07", "2020"));
//        DBOpBankAccount.initDBM();


        // Crate the db helper
        mDbm = new DataBaseMgt(this);
        DBOpBankAccount.initDBM(mDbm);

//        for (BankAccount bk : bk_list) {
//            DBOpBankAccount.insertBankAccount(bk);
//        }

//        BankAccount ba_user = new BankAccount("abc@google.com", "", "", "", "");
        BankAccount ba_user = new BankAccount(TempStorage.getAccount().getEmail(), "", "", "", "");
        final Cursor acc_cursor = DBOpBankAccount.searchAllBankAccoutCursor(ba_user);
        Log.d("MyDebug", "Number of record for this user: " + acc_cursor.getCount());

        lv_cards = findViewById(R.id.lv_all_cards);

        configureCursorAdapter(acc_cursor);

        lv_cards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor cursor_item = (Cursor) lv_cards.getItemAtPosition(i);

                String card_no = cursor_item.getString(cursor_item.getColumnIndex(BankAccount.COL_CARD_NO));
                card_no = card_no.substring(card_no.length() - 4);
                Toast.makeText(getBaseContext(),
                        "Selected " + card_no,
                        Toast.LENGTH_SHORT).show();

                updateDefaultCardSetting(cursor_item);
            }
        });
    }

    private void updateDefaultCardSetting(Cursor cursor_item) {
        String email = cursor_item.getString(cursor_item.getColumnIndex(BankAccount.COL_EMAIL));
        String card_no = cursor_item.getString(cursor_item.getColumnIndex(BankAccount.COL_CARD_NO));
//        DateFormat df = new DateFormat();
//        String format_str = "mm/dd/yyyy kk:mm:ss";
//        String current_date = df.format(format_str, Calendar.getInstance().getTime()).toString();
        String current_date = DateStringConvert.dateConvertToString(new Date());
        SettingItem setting = new SettingItem(email, SettingItem.ITEM_DEFAULT_CARD, card_no, current_date);

        if (mDbm != null) {
            DBOpSetting.initDBM(mDbm);
            if (!DBOpSetting.updateSetting(setting)) {
                DBOpSetting.insertSetting(setting);
            }
            Toast.makeText(getBaseContext(),
                    "Current time " + current_date,
                    Toast.LENGTH_SHORT).show();
        }

    }

    private void configureCursorAdapter(Cursor cs) {
        lv_cards.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        if (cs.getCount() > 0) {
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                    R.layout.checked_credit_card_entry,
                    cs,
                    new String[]{BankAccount.COL_CARD_NO, BankAccount.COL_EXPIRE_MONTH},
                    new int[]{R.id.card_lv_item, R.id.card_exp_date}, 0);

            adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
                @Override
                public boolean setViewValue(View view, Cursor cursor, int i) {
                    if (view.getId() == R.id.card_lv_item) {
                        String card_no = cursor.getString(cursor.getColumnIndex(BankAccount.COL_CARD_NO));
                        String card_last4 = "";
                        if (card_no.length() > 4) {
                            card_last4 = card_no.substring(card_no.length() - 4);
                        }
                        ((TextView) view).setText("Ending with " + card_last4);
                        return true;
                    }
                    if (view.getId() == R.id.card_exp_date) {
                        String year = cursor.getString(cursor.getColumnIndex(BankAccount.COL_EXPIRE_YEAR));
                        String month = cursor.getString(cursor.getColumnIndex(BankAccount.COL_EXPIRE_MONTH));
                        ((TextView) view).setText(month + "/" + year);
                        return true;
                    }
                    return false;
                }
            });
            lv_cards.setAdapter(adapter);
        } else {
            Toast.makeText(getBaseContext(), "User not found!", Toast.LENGTH_LONG).show();
        }
    }

}
