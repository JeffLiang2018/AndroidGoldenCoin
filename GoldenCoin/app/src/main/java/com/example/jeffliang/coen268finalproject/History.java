package com.example.jeffliang.coen268finalproject;

import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.jeffliang.coen268finalproject.database.DBOpTradeRecord;
import com.example.jeffliang.coen268finalproject.database.DataBaseMgt;
import com.example.jeffliang.coen268finalproject.entities.TradeRecord;
import com.example.jeffliang.coen268finalproject.util.TempStorage;

public class History extends AppCompatActivity {

    private TextView no_history_tv;
    private ListView history_lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Toolbar myToolbar = findViewById(R.id.no_history_toolbar);
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

        //TODO remove in merged app
        // Testing here to insert a record and display the history.
        DataBaseMgt dbm = new DataBaseMgt(this);
        DBOpTradeRecord.initDBM(dbm);
//        TradeRecord r1 = new TradeRecord("ang.zhang@scu.edu", "Golden Coin", "GC", 1, 100.0, 190, 3, "07/20");
//        Log.d("MyDebug", "Insert record");
//        DBOpTradeRecord.insertTradeRecord(r1);

        TradeRecord r1 = new TradeRecord();
        r1.setEmail(TempStorage.getAccount().getEmail());
        Cursor cc = DBOpTradeRecord.searchAllTradeRecordCursor(r1);
        Log.d("MyDebug", "Number of record: " + cc.getCount());

        no_history_tv = findViewById(R.id.no_trading_history);
        history_lv = findViewById(R.id.trading_history_lv);

        if (cc.getCount() > 0) {
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_2,
                    cc,
                    new String[]{TradeRecord.COL_EMAIL, TradeRecord.COL_BUY_SELL_FLAG},
                    new int[]{android.R.id.text1, android.R.id.text2}, 0);


            no_history_tv.setVisibility(View.INVISIBLE);

            adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
                @Override
                public boolean setViewValue(View view, Cursor cursor, int i) {
                    if (view.getId() == android.R.id.text2) {
                        String msg = cursor.getString(cursor.getColumnIndex(TradeRecord.COL_COIN_AMOUNT));
                        String tran_date = cursor.getString(cursor.getColumnIndex(TradeRecord.COL_TRAN_DATE));
                        if (cursor.getInt(i) == TradeRecord.SELL_VALUE) {
                            msg = "Sold " + msg + " coins";
                        } else {
                            msg = "Bought " + msg + " coins";
                        }
                        ((TextView) view).setText(msg + " on " + tran_date);
                        return true;
                    }
                    return false;
                }
            });
            history_lv.setAdapter(adapter);
        } else {
            no_history_tv.setVisibility(View.VISIBLE);
        }

    }
}
