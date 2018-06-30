package com.example.jeffliang.coen268finalproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/*******************************************/
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

// async get
import android.os.AsyncTask;

import com.example.jeffliang.coen268finalproject.database.DBOpGoldenCoinAcct;
import com.example.jeffliang.coen268finalproject.database.DBOpTradeRecord;
import com.example.jeffliang.coen268finalproject.database.DataBaseMgt;
import com.example.jeffliang.coen268finalproject.entities.GoldenCoinAcct;
import com.example.jeffliang.coen268finalproject.entities.SettingItem;
import com.example.jeffliang.coen268finalproject.entities.TradeRecord;
import com.example.jeffliang.coen268finalproject.util.DateStringConvert;
import com.example.jeffliang.coen268finalproject.util.TempStorage;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.github.mikephil.charting.formatter.ValueFormatter;


import org.json.*;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
/*******************************************/





import com.example.jeffliang.coen268finalproject.util.ActivitiesControl;


public class MainPage extends AppCompatActivity{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToogle;
    private Toolbar mToolbar;
    private NavigationView navigationView;

    final Context context = this;

    /*******************************************/
    private String cryptoUrl = "https://api.coinmarketcap.com/v2/ticker/?convert=usd";
    private String okUrlhead= "https://rest.coinapi.io/v1/ohlcv/BITSTAMP_SPOT_";
    private String okUrlEnd="_USD/latest?period_id=1DAY&apikey=599242FE-4CDC-40F7-9768-E8832AFC204A";
    //"https://www.okcoin.cn/api/v1/trades.do?symbol=btc_cny";

    //String cur_coin = "BTC";

    float prices[] = new float[5]; //0.BTC, 1.ETH, 2.BCH, 3.LTC, 4.DOGE
    float day_percentages[] = new float[5];
    String token[] = new String[5];
    String token_abbr[] = new String[5];

    TextView tokenViews[] = new TextView[5];
    TextView portfolio;
    String[] tkn_split;
    float[] cur_amount = {0,0,0,0,0,0}; //0.BTC, 1.ETH, 2.BCH, 3.LTC, 4.DOGE 5.USD

    float d_prc;

    private ArrayList<String> list=new ArrayList<String>();
    DataBaseMgt dbm = new DataBaseMgt(this);
    ;

    /*******************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);

        setContentView(R.layout.loading_layout);
        setRepeatingAsyncTask();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToogle.onOptionsItemSelected(item)) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void clickNavigationPage(View view) {
        ActivitiesControl.mainPage = this;
        Toast.makeText(this, "Click Navigation Page", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Navigation.class);
        startActivity(intent);
    }


// open main page
public void openApp(View view)
{


    // Do something in response to button click
    setContentView(R.layout.activity_main_page);
    appendTokenData(token_abbr,day_percentages,prices);
    //
    mToolbar = (Toolbar)findViewById(R.id.nav_action);
    setSupportActionBar(mToolbar);

    mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
    mToogle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

    mDrawerLayout.addDrawerListener(mToogle);
    mToogle.syncState();

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    navigationView = (NavigationView)findViewById(R.id.navigation_view);
    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int size = navigationView.getMenu().size();

            switch(item.getItemId()) {
                case R.id.nav_account: {
//                        System.out.println("Account is clicked!");
                    Toast.makeText(MainPage.this, "Account is clicked!", Toast.LENGTH_SHORT).show();
                    break;
                }

                case R.id.nav_banking: {
//                        System.out.println("Banking is clicked!");    // test only
                    Toast.makeText(MainPage.this, "Banking is clicked!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainPage.this, Banking.class));
                    break;
                }

                case R.id.nav_history: {
//                        System.out.println("History is clicked!");    // test only
//                        Toast.makeText(MainPage.this, "History is clicked!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainPage.this, History.class));
                    break;
                }

                case R.id.nav_notification: {
//                        System.out.println("Notification is clicked!");    // test only
//                        Toast.makeText(MainPage.this, "Notification is clicked!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainPage.this, NotificationList.class));
                    break;
                }

                case R.id.nav_setting: {
//                        System.out.println("SettingItem is clicked!");    // test only
//                        Toast.makeText(context, "SettingItem is clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainPage.this, Setting.class);
                    startActivity(intent);
                    break;
                }

                case R.id.nav_logout: {
//                        System.out.println("LogOut is clicked!");    // test only
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainPage.this);
                    builder.setTitle("Exit");
                    builder.setMessage("Are you sure to exit?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
                    builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                    break;
                }
            }

            return true;
        }
    });

}

    public void showPortfolioValue(){
        // todo portfolio
        portfolio = findViewById(R.id.port_value);
        portfolio.setText("Portfolio Value: ");
        float kk = cur_amount[0]*prices[0]+cur_amount[1]*prices[1]+cur_amount[2]*prices[2]+cur_amount[3]*prices[3]+cur_amount[4]*prices[4];
        portfolio.append("$"+ kk);
    }



    // back to crypto detail
    public void backToDetail(View view)
    {
        // Do something in response to button click
        setContentView(R.layout.currency_detail);
        MyAsyncTask jt = new MyAsyncTask();

        jt.execute(okUrlhead+tkn_split[2]+okUrlEnd);
        list.clear();
        TextView detail_price = (TextView)findViewById(R.id.textView_d_p);
        detail_price.setText(" "+tkn_split[2]+" PRICE:      $"+d_prc);
    }

    public void backToMain(View view)
    {
        // Do something in response to button click
        //gt.execute(cryptoUrl);
        setRepeatingAsyncTask();
        setContentView(R.layout.activity_main_page);
        appendTokenData(token_abbr,day_percentages,prices);
        mToolbar = (Toolbar)findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        mToogle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToogle);
        mToogle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int size = navigationView.getMenu().size();

                switch(item.getItemId()) {
                    case R.id.nav_account: {
//                        System.out.println("Account is clicked!");
                        Toast.makeText(MainPage.this, "Account is clicked!", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    case R.id.nav_banking: {
//                        System.out.println("Banking is clicked!");    // test only
                        Toast.makeText(MainPage.this, "Banking is clicked!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainPage.this, Banking.class));
                        break;
                    }

                    case R.id.nav_history: {
//                        System.out.println("History is clicked!");    // test only
//                        Toast.makeText(MainPage.this, "History is clicked!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainPage.this, History.class));
                        break;
                    }

                    case R.id.nav_notification: {
//                        System.out.println("Notification is clicked!");    // test only
//                        Toast.makeText(MainPage.this, "Notification is clicked!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainPage.this, NotificationList.class));
                        break;
                    }

                    case R.id.nav_setting: {
//                        System.out.println("SettingItem is clicked!");    // test only
//                        Toast.makeText(context, "SettingItem is clicked", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainPage.this, Setting.class);
                        startActivity(intent);
                        break;
                    }

                    case R.id.nav_logout: {
//                        System.out.println("LogOut is clicked!");    // test only
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainPage.this);
                        builder.setTitle("Exit");
                        builder.setMessage("Are you sure to exit?");
                        builder.setCancelable(false);
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        });
                        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();
                        break;
                    }
                }

                return true;
            }
        });
        appendTokenData(token_abbr,day_percentages,prices);
        //gt.cancel(true);

    }



    public void openDetail(View view)
    {
        // Do something in response to button click
        //gt.cancel(true);
        TextView tkn = (TextView) view;
        tkn_split = tkn.getText().toString().split("\\s+");
        d_prc=-1;
        setContentView(R.layout.currency_detail);
        MyAsyncTask jt = new MyAsyncTask();
        jt.execute(okUrlhead+tkn_split[2]+okUrlEnd);
        list.clear();

        switch (tkn_split[2]){
            case "BTC":
                d_prc = prices[0];
                //iv.setImageResource(R.drawable.btc);
                break;
            case "ETH":
                d_prc = prices[1];
                //iv.setImageResource(R.drawable.eth);
                break;
            case "BCH":
                d_prc = prices[2];
                //iv.setImageResource(R.drawable.bch);
                break;
            case "LTC":
                d_prc = prices[3];
                //iv.setImageResource(R.drawable.ltc);

                break;
            case "DOGE":
                d_prc = prices[4];
                //iv.setImageResource(R.drawable.doge);

                break;

            default:
                break;
        }
        System.out.println(tkn_split[2]);
        TextView detail_price = (TextView)findViewById(R.id.textView_d_p);
        detail_price.setText(" "+tkn_split[2]+" PRICE:      $"+d_prc);

    }
    public void openBuy(View view)
    {
        // Do something in response to button click
        setContentView(R.layout.buy_input);
        setAttr();

    }

    public void openSell(View view)
    {
        // Do something in response to button click
        setContentView(R.layout.sell_input);
        setAttr();

    }
    public void setAttr(){
        float amt=0;
        float pp=-1;
        switch (tkn_split[2]){
            case "BTC":
                pp=prices[0];
                amt = cur_amount[0];
                break;
            case "ETH":
                pp=prices[1];

                amt = cur_amount[1];
                break;
            case "BCH":
                pp=prices[2];

                amt = cur_amount[2];

                break;
            case "LTC":
                pp=prices[3];

                amt = cur_amount[3];

                break;
            case "DOGE":
                pp=prices[4];

                amt = cur_amount[4];

                break;
            default:
                System.out.print("NO UNIT PRICE");
                break;
        }
        TextView your_c = findViewById(R.id.your_crypto);
        your_c.setText("Your "+tkn_split[2]+": ");
        TextView cur_c = findViewById(R.id.cur_crypto);
        cur_c.setText(tkn_split[2]);
        TextView cur_c_amount = findViewById(R.id.your_amount);
        cur_c_amount.setText(""+amt);
        TextView cur_c_amount_to_usd = findViewById(R.id.your_amount_usd_value);
        double amtpp = Double.parseDouble(String.format("%.5f", amt*pp));
        cur_c_amount_to_usd.setText(""+amtpp);
        TextView cur_usd = findViewById(R.id.remaining_usd);

        DBOpGoldenCoinAcct.initDBM(dbm);
        GoldenCoinAcct gcaSearch = new GoldenCoinAcct();
        gcaSearch.setEmail(TempStorage.getAccount().getEmail());
        GoldenCoinAcct gcaResult = DBOpGoldenCoinAcct.searchGoldenCoinAcct(gcaSearch);
        double gcBalance = gcaResult.getBalance();
        gcBalance = Double.parseDouble(String.format("%.2f", gcBalance));


        cur_usd.setText(""+gcBalance);

    }
    public void buyCoin(View view)
    {
        EditText buytext = (EditText) findViewById(R.id.edit_buy);
        float value = -1;
        if ((buytext.getText().toString().length() > 0)){
            value = Float.parseFloat(buytext.getText().toString());
        }
        if ((buytext.getText().toString().length() > 0) && (value > 0)) {

            // Do something in response to button click
            //setContentView(R.layout.sell_input);x
            AlertDialog.Builder builder = new AlertDialog.Builder(MainPage.this);
            // Set a title for alert dialog
            builder.setTitle("Select your answer.");

            // Ask the final question
            builder.setMessage("Are you sure to buy?");

            // Set the alert dialog yes button click listener
            final float finalValue = value;
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Do something when user clicked the Yes button


                    //todo implement db operation
                    Date date = new Date();
                    System.out.print("buy complete");
                    String date_String = DateStringConvert.dateConvertToString(date);
                    float unit_price = -1;
                    switch (tkn_split[2]){
                        case "BTC":
                            unit_price = prices[0];
                            cur_amount[0]+=finalValue;
                            break;
                        case "ETH":
                            unit_price = prices[1];
                            cur_amount[1]+=finalValue;

                            break;
                        case "BCH":
                            unit_price = prices[2];
                            cur_amount[2]+=finalValue;

                            break;
                        case "LTC":
                            unit_price = prices[3];
                            cur_amount[3]+=finalValue;

                            break;
                        case "DOGE":
                            unit_price = prices[4];
                            cur_amount[4]+=finalValue;

                            break;
                            default:
                                System.out.print("NO UNIT PRICE");
                                break;
                    }
                    DBOpGoldenCoinAcct.initDBM(dbm);
                    GoldenCoinAcct gcaSearch = new GoldenCoinAcct();
                    gcaSearch.setEmail(TempStorage.getAccount().getEmail());
                    GoldenCoinAcct gcaResult = DBOpGoldenCoinAcct.searchGoldenCoinAcct(gcaSearch);
                    double gcBalance = gcaResult.getBalance();
                    gcaResult.setBalance(gcBalance-finalValue*unit_price);
                    DBOpGoldenCoinAcct.updateGoldenCoinAcct(gcaResult);
                    cur_amount[5] = (float)gcBalance;
                    TradeRecord tr = new TradeRecord(TempStorage.getAccount().getEmail(), tkn_split[0],tkn_split[2],1,unit_price, finalValue,unit_price*finalValue,date_String);
                    DBOpTradeRecord.initDBM(dbm);
                    DBOpTradeRecord.insertTradeRecord(tr);

                    setContentView(R.layout.buy_complete);
                }
            });

            // Set the alert dialog no button click listener
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Do something when No button clicked
                    Toast.makeText(getApplicationContext(),
                            "No Button Clicked",Toast.LENGTH_SHORT).show();
                }
            });

            AlertDialog dialog = builder.create();
            // Display the alert dialog on interface
            dialog.show();

        }else{
            Toast.makeText(getApplicationContext(),
                    "Please enter value which is bigger than 0",Toast.LENGTH_SHORT).show();
        }



    }

    public void sellCoin(View view)
    {
        EditText selltext = (EditText) findViewById(R.id.edit_sell);
        float value = -1;
        if ((selltext.getText().toString().length() > 0)){
            value = Float.parseFloat(selltext.getText().toString());
        }

        if ((selltext.getText().toString().length() > 0) && (value > 0)) {
            // Do something in response to button click
            //setContentView(R.layout.sell_input);
            AlertDialog.Builder builder = new AlertDialog.Builder(MainPage.this);
            // Set a title for alert dialog
            builder.setTitle("Select your answer.");

            // Ask the final question
            builder.setMessage("Are you sure to sell?");

            // Set the alert dialog yes button click listener
            final float finalValue = value;
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Do something when user clicked the Yes button

                    setContentView(R.layout.buy_complete);
                    //todo implement db operation
                    Date date = new Date();
                    System.out.print("buy complete");
                    String date_String = DateStringConvert.dateConvertToString(date);
                    float unit_price = -1;
                    switch (tkn_split[2]){
                        case "BTC":
                            unit_price = prices[0];
                            cur_amount[0]-=finalValue;

                            break;
                        case "ETH":
                            unit_price = prices[1];
                            cur_amount[1]-=finalValue;

                            break;
                        case "BCH":
                            unit_price = prices[2];
                            cur_amount[2]-=finalValue;

                            break;
                        case "LTC":
                            unit_price = prices[3];
                            cur_amount[3]-=finalValue;

                            break;
                        case "DOGE":
                            unit_price = prices[4];
                            cur_amount[4]-=finalValue;

                            break;
                        default:
                            System.out.print("NO UNIT PRICE");
                            break;
                    }
                    DBOpGoldenCoinAcct.initDBM(dbm);
                    GoldenCoinAcct gcaSearch = new GoldenCoinAcct();
                    gcaSearch.setEmail(TempStorage.getAccount().getEmail());
                    GoldenCoinAcct gcaResult = DBOpGoldenCoinAcct.searchGoldenCoinAcct(gcaSearch);
                    double gcBalance = gcaResult.getBalance();
                    gcaResult.setBalance(gcBalance+finalValue*unit_price);
                    DBOpGoldenCoinAcct.updateGoldenCoinAcct(gcaResult);
                    cur_amount[5] = (float)gcBalance;

                    TradeRecord tr = new TradeRecord(TempStorage.getAccount().getEmail(), tkn_split[0],tkn_split[2],0,unit_price, finalValue,unit_price*finalValue,date_String);
                    DBOpTradeRecord.initDBM(dbm);
                    DBOpTradeRecord.insertTradeRecord(tr);

                    setContentView(R.layout.buy_complete);
                }
            });

            // Set the alert dialog no button click listener
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Do something when No button clicked
                    Toast.makeText(getApplicationContext(),
                            "No Button Clicked",Toast.LENGTH_SHORT).show();
                }
            });

            AlertDialog dialog = builder.create();
            // Display the alert dialog on interface
            dialog.show();


        }else{
            Toast.makeText(getApplicationContext(),
                    "Please enter value which is bigger than 0",Toast.LENGTH_SHORT).show();
        }


    }


    private class GetUrlContentTask extends AsyncTask<String, Integer, JSONObject> {
        HttpURLConnection urlConnection;

        @Override
        protected JSONObject doInBackground(String... urls) {
            URL url = null;
            //curr_coin = urls[1];
            try {
                url = new URL(urls[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            StringBuilder result = new StringBuilder();

            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                JSONObject json = new JSONObject(result.toString());
                return json;
            }catch( Exception e) {
                e.printStackTrace();
            }
            finally {
                urlConnection.disconnect();
            }


            return null;
        }

        protected void onProgressUpdate(Integer... progress) {
            //setProgressPercent(progress[0]);
        }

        protected void onPostExecute(JSONObject json) {
            // this is executed on the main thread after the process is over
            float p[] = new float[5]; //0.BTC, 1.ETH, 2.BCH, 3.LTC, 4.DOGE
            float d_p[] = new float[5];
            String tok[] = new String[5];
            String abb[] = new String[5];

            try {
                //System.out.println(json.getJSONObject("data").getJSONObject("1").get("name"));
                // token name
                tok[0] = json.getJSONObject("data").getJSONObject("1").get("name").toString();
                tok[1] = json.getJSONObject("data").getJSONObject("1027").get("name").toString();
                String tok2 = json.getJSONObject("data").getJSONObject("1831").get("website_slug").toString();
                tok[2] = tok2.substring(0, 1).toUpperCase() + tok2.substring(1);
                tok[3] = json.getJSONObject("data").getJSONObject("2").get("name").toString();
                tok[4] = json.getJSONObject("data").getJSONObject("74").get("name").toString();

                //token abbv
                abb[0] = json.getJSONObject("data").getJSONObject("1").get("symbol").toString();
                abb[1] = json.getJSONObject("data").getJSONObject("1027").get("symbol").toString();
                abb[2] = json.getJSONObject("data").getJSONObject("1831").get("symbol").toString();
                abb[3] = json.getJSONObject("data").getJSONObject("2").get("symbol").toString();
                abb[4] = json.getJSONObject("data").getJSONObject("74").get("symbol").toString();

                // prices
                p[0] = Float.parseFloat(json.getJSONObject("data").getJSONObject("1").getJSONObject("quotes").getJSONObject("USD").get("price").toString());
                p[1] = Float.parseFloat(json.getJSONObject("data").getJSONObject("1027").getJSONObject("quotes").getJSONObject("USD").get("price").toString());
                p[2] = Float.parseFloat(json.getJSONObject("data").getJSONObject("1831").getJSONObject("quotes").getJSONObject("USD").get("price").toString());
                p[3] = Float.parseFloat(json.getJSONObject("data").getJSONObject("2").getJSONObject("quotes").getJSONObject("USD").get("price").toString());
                p[4] = Float.parseFloat(json.getJSONObject("data").getJSONObject("74").getJSONObject("quotes").getJSONObject("USD").get("price").toString());

                //day_percentages
                d_p[0] = Float.parseFloat(json.getJSONObject("data").getJSONObject("1").getJSONObject("quotes").getJSONObject("USD").get("percent_change_24h").toString());
                d_p[1] = Float.parseFloat(json.getJSONObject("data").getJSONObject("1027").getJSONObject("quotes").getJSONObject("USD").get("percent_change_24h").toString());
                d_p[2] = Float.parseFloat(json.getJSONObject("data").getJSONObject("1831").getJSONObject("quotes").getJSONObject("USD").get("percent_change_24h").toString());
                d_p[3] = Float.parseFloat(json.getJSONObject("data").getJSONObject("2").getJSONObject("quotes").getJSONObject("USD").get("percent_change_24h").toString());
                d_p[4] = Float.parseFloat(json.getJSONObject("data").getJSONObject("74").getJSONObject("quotes").getJSONObject("USD").get("percent_change_24h").toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }

            loadTokenData(tok,abb,p,d_p);
        }
    }


    /*
     * chart data async
     * */
    private class MyAsyncTask extends AsyncTask<String, Void, JSONObject[] >{
        private String abcString;
        HttpURLConnection urlConnection;
        HttpURLConnection urlConnection2;




        @Override
        protected JSONObject[] doInBackground(String... urls) {
            String urll2="https://www.okcoin.cn/api/v1/ticker.do?symbol=btc_cny";

            URL url = null;
            URL url2 = null;

            try {
                url = new URL(urls[0]);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                url2 = new URL(urll2);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            StringBuilder result = new StringBuilder();
            StringBuilder result2 = new StringBuilder();


            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection2 = (HttpURLConnection) url2.openConnection();

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                InputStream in2 = new BufferedInputStream(urlConnection2.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                BufferedReader reader2 = new BufferedReader(new InputStreamReader(in2));

                String line;
                String line2;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                while ((line2 = reader2.readLine()) != null) {
                    result2.append(line2);
                    //System.out.println(result2.toString());
                }
                JSONArray jsonArray=new JSONArray(result.toString());

                JSONObject json;
                JSONObject json2 = new JSONObject(result2.toString());
                JSONObject json3;
                json3=json2.getJSONObject("ticker");
                abcString=json3.getString("last");

                JSONObject[] ja = new JSONObject[3];

                for (int i = 0; i < jsonArray.length(); i++) {
                    json=jsonArray.getJSONObject(i);
                    list.add(json.getString("price_close")) ;
                    Log.d("add", ""+list.get(i));
//							Float.parseFloat()
                }
                Log.d("add1", ""+abcString);
                //ja[0] = json;
                ja[1] = json2;
                ja[2] = json3;
                return ja;
            }catch( Exception e) {
                e.printStackTrace();
            }
            finally {
                urlConnection.disconnect();
            }



            return null;
        }




        @Override
        protected void onPostExecute(JSONObject[] json) {
            // TODO Auto-generated method stub
            System.out.print(list);

            Collections.reverse(list);
            //super.onPostExecute(result);
            Log.d("mnb", "HelloWord");
            // 制作7个数据点（沿x坐标轴）
            LineChart chart = (LineChart) findViewById(R.id.chart);
            LineData mLineData = makeLineData();
            setChartStyle(chart, mLineData, Color.WHITE);


        }

    }




    /////////////////////
    private void loadTokenData(String[] tok, String[] abb, float[] p, float[] d_p) {
        //handle value
        for (int i =0;i<5;i++){
            System.out.println(tok[i]+abb[i]+p[i]+d_p[i]);
        }
        token = tok;
        token_abbr = abb;
        prices = p;
        day_percentages = d_p;
    }

    private void appendTokenData(String[] t, float[] p, float[] pr){
        tokenViews[0] = (TextView)findViewById(R.id.btc_text);
        tokenViews[1] = (TextView)findViewById(R.id.eth_text);
        tokenViews[2] = (TextView)findViewById(R.id.bch_text);
        tokenViews[3] = (TextView)findViewById(R.id.ltc_text);
        tokenViews[4] = (TextView)findViewById(R.id.dgc_text);
        //new GetUrlContentTask().execute(ethUrl,cur_coin);
        for (int i = 0;i<5;i++){
            tokenViews[i].setText(token[i]);
            tokenViews[i].append(":     $"+pr[i]+"\n"+t[i]+"           "+p[i]+"%");
        }
        showPortfolioValue();
    }
    /*
     * Now call setRepeatingAsyncTask() from your onCreate() or onResume().
     * in the onDestroy() method of your Activity, use handler.removeCallbacks(previouslyStartedRunnable);.
     * Declare both the Handler and the Runnable as class fields.
     * This will do the trick
     * */
    private void setRepeatingAsyncTask() {

        final Handler handler = new Handler();
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            GetUrlContentTask jsonTask = new GetUrlContentTask();
                            JSONObject str_result= jsonTask.execute(cryptoUrl).get();


                        } catch (Exception e) {
                            // error, do something
                        }
                    }
                });
            }
        };

        timer.schedule(task, 0, 60*1000);  // interval of 1min

    }



    /**
     *
     * 图表样式设置
     *
     *
     */

    // 设置chart显示的样式
    private void setChartStyle(LineChart mLineChart, LineData lineData,
                               int color) {
        // 是否在折线图上添加边框
        mLineChart.setDrawBorders(false);


        // 如果没有数据的时候，会显示这个，类似listview的emtpyview
        mLineChart.setNoDataText("Server not responding");

        // 是否绘制背景颜色。
        // 如果mLineChart.setDrawGridBackground(false)，
        // 那么mLineChart.setGridBackgroundColor(Color.CYAN)将失效;
        mLineChart.setDrawGridBackground(false);
        mLineChart.setGridBackgroundColor(Color.CYAN);

        // 触摸
        mLineChart.setTouchEnabled(true);

        // 拖拽
        mLineChart.setDragEnabled(true);

        // 缩放
        mLineChart.setScaleEnabled(true);

        mLineChart.setPinchZoom(false);
        // 隐藏右边 的坐标轴
//        mLineChart.getAxisRight().setEnabled(false);
        // 让x轴在下面
        mLineChart.getXAxis().setPosition(XAxisPosition.BOTTOM);

        // // 隐藏左边坐标轴横网格线
        // mLineChart.getAxisLeft().setDrawGridLines(false);
        // // 隐藏右边坐标轴横网格线
        // mLineChart.getAxisRight().setDrawGridLines(false);
        // // 隐藏X轴竖网格线
        mLineChart.getXAxis().setDrawGridLines(false);
        mLineChart.getAxisRight().setEnabled(false); // 隐藏右边 的坐标轴(true不隐藏)

        mLineChart.getXAxis().setPosition(XAxisPosition.BOTTOM); // 让x轴在下面
        // 设置背景
//        mLineChart.setBackgroundColor(color);

        // 设置x,y轴的数据
        mLineChart.setData(lineData);

        XAxis mXAxis = mLineChart.getXAxis();  //获取X轴
        mXAxis.setAxisLineColor(Color.WHITE);
        mXAxis.setAxisLineWidth(1f);
        mXAxis.setTextColor(Color.WHITE);

        YAxis mLeftYAxis =mLineChart.getAxisLeft();//获取Y轴
        mLeftYAxis.setAxisLineColor(Color.WHITE);
        mLeftYAxis.setAxisLineWidth(1f);
        mLeftYAxis.setTextColor(Color.WHITE);
        mLeftYAxis.setGridColor(Color.WHITE);
//        mLeftYAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);



        // 设置比例图标示，就是那个一组y的value的
        Legend mLegend = mLineChart.getLegend();

        mLegend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        mLegend.setForm(Legend.LegendForm.CIRCLE);// 样式
        mLegend.setFormSize(0.0f);// 字体
        mLegend.setTextColor(Color.WHITE);// 颜色

        // 沿x轴动画，时间2000毫秒。
        mLineChart.animateX(2000);
    }

    /**
     *  数据点的数量
     * 	图表数据设置
     */
    //方法参数int count
    private LineData makeLineData() {
        ArrayList<String> x = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++) {
            // x轴显示的数据
            x.add(""+i);
        }
        // y轴的数据
        ArrayList<Entry> y = new ArrayList<Entry>();
        for (int i = 0; i < list.size(); i++) {
//            float val = (float) (Math.random() * 100000);
            float val = Float.parseFloat(list.get(i));
            Entry entry = new Entry(val, i);
            y.add(entry);
        }

        // y轴数据集
        LineDataSet mLineDataSet = new LineDataSet(y, "");

        // 用y轴的集合来设置参数
        // 线宽
        mLineDataSet.setLineWidth(2.0f);

        // 显示的圆形大小
        mLineDataSet.setCircleSize(0.0f);

        // 折线的颜色
        mLineDataSet.setColor(Color.WHITE);

        // 圆球的颜色
        mLineDataSet.setCircleColor(Color.GREEN);

        // 设置mLineDataSet.setDrawHighlightIndicators(false)后，
        // Highlight的十字交叉的纵横线将不会显示，
        // 同时，mLineDataSet.setHighLightColor(Color.CYAN)失效。

        mLineDataSet.setDrawHighlightIndicators(true);


        // 按击后，十字交叉线的颜色
        mLineDataSet.setHighLightColor(Color.CYAN);

        // 设置这项上显示的数据点的字体大小。
        mLineDataSet.setValueTextSize(10.0f);
        mLineDataSet.setValueTextColor(Color.WHITE);




        // mLineDataSet.setDrawCircleHole(true);

        // 改变折线样式，用曲线。
        // mLineDataSet.setDrawCubic(true);
        // 默认是直线
        // 曲线的平滑度，值越大越平滑。
        // mLineDataSet.setCubicIntensity(0.2f);

        // 填充曲线下方的区域，红色，半透明。
        // mLineDataSet.setDrawFilled(true);
        // mLineDataSet.setFillAlpha(128);
        // mLineDataSet.setFillColor(Color.RED);


        // 填充折线上数据点、圆球里面包裹的中心空白处的颜色。
        mLineDataSet.setCircleColorHole(Color.YELLOW);



        // 设置折线上显示数据的格式。如果不设置，将默认显示float数据格式。
        mLineDataSet.setValueFormatter(new ValueFormatter() {



            @Override
            public String getFormattedValue(float value, Entry entry,
                                            int dataSetIndex, ViewPortHandler viewPortHandler) {
                // TODO Auto-generated method stub
                int n = (int) value;
//                String s = "\u00A5" + n;
                String s ="";
                return s;
            }
        });

        ArrayList<LineDataSet> mLineDataSets = new ArrayList<LineDataSet>();
        mLineDataSets.add(mLineDataSet);

        LineData mLineData = new LineData( x, mLineDataSet);

        return mLineData;
    }





}
