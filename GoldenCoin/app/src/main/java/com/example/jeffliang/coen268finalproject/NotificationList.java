package com.example.jeffliang.coen268finalproject;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jeffliang.coen268finalproject.database.DBOpNotification;
import com.example.jeffliang.coen268finalproject.database.DataBaseMgt;
import com.example.jeffliang.coen268finalproject.entities.Notification;
import com.example.jeffliang.coen268finalproject.util.DateStringConvert;
import com.example.jeffliang.coen268finalproject.util.TempStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class NotificationList extends AppCompatActivity {

    private ListView notif_list;
    private List<Notification> notificationList;
    private ArrayAdapter<Notification> notificationAdapter;

    DataBaseMgt dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);

        Toolbar myToolbar = findViewById(R.id.notification_list_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setTitle("Notification List");
        }

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dbm = new DataBaseMgt(this);
        DBOpNotification.initDBM(dbm);
        Notification noSearch = new Notification();
        noSearch.setEmail(TempStorage.getAccount().getEmail());
        notificationList =DBOpNotification.searchAllNotification(noSearch);
        Collections.sort(notificationList);

        notif_list = findViewById(R.id.notification);

        notificationAdapter = new ArrayAdapter<Notification>(this, R.layout.notification_list, notificationList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                Notification no = notificationList.get(position);
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.notification_list, null, false);
                }

                TextView title = convertView.findViewById(R.id.notif_title);
                TextView content = convertView.findViewById(R.id.notif_content);

                if(no.getRead().equals("0")){//not read
                    title.setTypeface(Typeface.DEFAULT_BOLD, 0);
                }else{
                    title.setTypeface(Typeface.DEFAULT);
                }
                title.setText(no.getTitle());
                content.setText(no.getContent());

                TextView gen_date = convertView.findViewById(R.id.gen_date);
                gen_date.setText(no.getGen_date());

                return convertView;
            }
        };

        notif_list.setAdapter(notificationAdapter);

        notif_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Notification notif = notificationList.get(position);

                AlertDialog.Builder dialog = new AlertDialog.Builder(NotificationList.this);
                dialog.setTitle(notif.getTitle() +"\n");
                TextView textView = new TextView(NotificationList.this);

                String message = "This message was generated on " + notif.getGen_date() + "\n" + notif.getContent();
                textView.setText(message);

                dialog.setView(textView);
                dialog.show();

                if ("0".equals(notif.getRead())) {
                    notif.setRead("1");
                    notif.setLast_upd_dt(DateStringConvert.dateConvertToString(new Date()));
                    DBOpNotification.initDBM(dbm);
                    DBOpNotification.updateNotification(notif);

                    notificationList = DBOpNotification.searchAllNotification(notif);
                    Collections.sort(notificationList);
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
