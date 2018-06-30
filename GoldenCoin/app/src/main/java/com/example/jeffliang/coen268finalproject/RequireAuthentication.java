package com.example.jeffliang.coen268finalproject;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jeffliang.coen268finalproject.database.DBOpSetting;
import com.example.jeffliang.coen268finalproject.database.DataBaseMgt;
import com.example.jeffliang.coen268finalproject.entities.SettingItem;
import com.example.jeffliang.coen268finalproject.util.DateStringConvert;
import com.example.jeffliang.coen268finalproject.util.TempStorage;

import java.util.Date;

public class RequireAuthentication extends AppCompatActivity {

    public static int auth_index;
    public static int defaultIndex;

    DataBaseMgt dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_require_authentication);

        Toolbar myToolbar = findViewById(R.id.require_auth_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar bar = getSupportActionBar();
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);


        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


//        SettingItem si = new SettingItem();
//        si.setEmail(TempStorage.getAccount().getEmail());
//        si.setSetting_item(SettingItem.ITEM_AUTHENTICATION);
//
//        si = DBOpSetting.searchSetting(si);
//        defaultIndex = Integer.valueOf(si.getSetting_value()) - 1;

        // TODO: DefaultIndex need to be read from db.
        dbm = new DataBaseMgt(this);
        DBOpSetting.initDBM(dbm);
        SettingItem siSearch = new SettingItem(TempStorage.getAccount().getEmail(), SettingItem.ITEM_AUTHENTICATION, "", "");
        SettingItem siResult = DBOpSetting.searchSetting(siSearch);
        defaultIndex = Integer.valueOf(siResult.getSetting_value());
        Log.d("Jeff Debug:", "defaultIndex = " + defaultIndex);
//        defaultIndex = 2;

        String[] auth_settings_array = getResources().getStringArray(
                R.array.auth_time);

        ListView auth_times = findViewById(R.id.auth_time_settings);
        auth_times.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        ArrayAdapter<String> auth_adapter = new ArrayAdapter<String>(this, R.layout.authentication_item, auth_settings_array);
        auth_times.setAdapter(auth_adapter);

//        DataBaseMgt dbm = new DataBaseMgt(RequireAuthentication.this);
//        DBOpSetting.initDBM(dbm);

        auth_times.setItemChecked(defaultIndex - 1, true);

        auth_times.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Selected the authentication time setting
                auth_index = i + 1;

                SettingItem si = new SettingItem();
                // TODO: get the email from the current account from TempStorage?
                si.setEmail(TempStorage.getAccount().getEmail());
                si.setSetting_item(SettingItem.ITEM_AUTHENTICATION);
                si.setSetting_value(Integer.toString(auth_index));
                si.setLast_upd_dt(DateStringConvert.dateConvertToString(new Date()));
                if (!DBOpSetting.updateSetting(si)) {
                    DBOpSetting.insertSetting(si);
                }

                //TODO: Remove in merged app
                //Verify the setting is saved to db
                SettingItem si_after = DBOpSetting.searchSetting(si);
                Toast.makeText(getBaseContext(),
                        "Set to: " + si_after.getSetting_value() + " on " + si_after.getLast_upd_dt(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
