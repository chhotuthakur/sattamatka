package com.rkonline.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    protected ScrollView scrollView;
    protected TextView balance;
    protected CardView single;
    protected CardView jodi;
    protected CardView singlepatti;
    protected CardView doublepatti;
    protected CardView tripepatti;
    protected CardView halfsangam;
    protected CardView fullsangam;
    protected CardView ghantab;
    protected latonormal hometext;
    protected CardView crossing;
    protected CardView exit;
    protected CardView logout;
    protected CardView refresh;
    protected TextView supportno;
    protected CardView support;
    RecyclerView recyclerview;
    SharedPreferences preferences;
    String url;
    String is_gateway = "0";
    Button lang_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        lang_img =(Button) findViewById(R.id.lang_switch);
        lang_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.openOptionsMenu();
            }
        });


        initViews();
        url = constant.prefix + getString(R.string.home);
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = constant.whatsapplink;

                Uri uri = Uri.parse(url);
                Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(sendIntent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Process.killProcess(Process.myPid());
                System.exit(1);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.edit().clear().apply();
                Intent in = new Intent(getApplicationContext(), login.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);
                finish();
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Refreshing...", Toast.LENGTH_SHORT).show();
                apicall();
            }
        });

        preferences = getSharedPreferences(constant.prefs, MODE_PRIVATE);
        apicall();

        if (preferences.getString("wallet", null) != null) {
            balance.setText(preferences.getString("wallet", null));
        } else {
            balance.setText("Loading");
        }

        if (preferences.getString("homeline", null) != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                hometext.setText(Html.fromHtml(preferences.getString("homeline", null), Html.FROM_HTML_MODE_COMPACT));
            } else {
                hometext.setText(Html.fromHtml(preferences.getString("homeline", null)));
            }
        } else {
            hometext.setText("Loading...");
        }


        single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, bazar.class).putExtra("game", "single").setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        jodi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, bazar.class).putExtra("game", "jodi").setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        crossing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, bazar.class).putExtra("game", "crossing").setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        singlepatti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, bazar.class).putExtra("game", "singlepatti").setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        doublepatti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, bazar.class).putExtra("game", "doublepatti").setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        tripepatti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, bazar.class).putExtra("game", "tripepatti").setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        ghantab =(CardView)findViewById(R.id.ghanta_bajar);
        ghantab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,GhantaList.class);
                startActivity(i);
            }
        });

        halfsangam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, bazar.class).putExtra("game", "halfsangam").setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        fullsangam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, bazar.class).putExtra("game", "fullsangam").setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        crossing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, bazar.class).putExtra("game", "crossing").setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });


        Typeface face = Typeface.createFromAsset(getAssets(), "Oxygen-Bold.ttf");


        PrimaryDrawerItem home = new PrimaryDrawerItem().withName("Home").withIcon(R.drawable.house).withIdentifier(999).withTypeface(face);
        PrimaryDrawerItem account = new PrimaryDrawerItem().withName("My Profile").withIcon(R.drawable.user_icon).withIdentifier(1).withTypeface(face);
        PrimaryDrawerItem charts = new PrimaryDrawerItem().withName("Charts").withIdentifier(101).withIcon(R.drawable.chart_icon).withTypeface(face);
        PrimaryDrawerItem rate = new PrimaryDrawerItem().withName("Game Rate").withIdentifier(2).withIcon(R.drawable.rupee_icon).withTypeface(face);
        PrimaryDrawerItem earn = new PrimaryDrawerItem().withName("Refer and Earn").withIcon(R.drawable.refer_icon).withIdentifier(21).withTypeface(face);
        PrimaryDrawerItem notice = new PrimaryDrawerItem().withName("Notice").withIcon(R.drawable.info_icon).withIdentifier(3).withTypeface(face);
        PrimaryDrawerItem deposit = new PrimaryDrawerItem().withName("Deposit").withIcon(R.drawable.rupee_icon).withIdentifier(4).withTypeface(face);
        PrimaryDrawerItem withdraw = new PrimaryDrawerItem().withName("Withdrawal").withIcon(R.drawable.rupee_icon).withIdentifier(41).withTypeface(face);
        PrimaryDrawerItem ledger = new PrimaryDrawerItem().withName("Game Ledger").withIcon(R.drawable.two_arraw).withIdentifier(6).withTypeface(face);
        PrimaryDrawerItem transaction = new PrimaryDrawerItem().withName("Balance Enquiry").withIcon(R.drawable.wallet_icon).withIdentifier(8).withTypeface(face);
        PrimaryDrawerItem played = new PrimaryDrawerItem().withName("Played Match").withIcon(R.drawable.play_icon).withIdentifier(9).withTypeface(face);
        PrimaryDrawerItem howto = new PrimaryDrawerItem().withName("How to Play").withIcon(R.drawable.question).withIdentifier(10).withTypeface(face);
        PrimaryDrawerItem share = new PrimaryDrawerItem().withName("Share").withIcon(R.drawable.share_icon).withIdentifier(11).withTypeface(face);
        PrimaryDrawerItem logout = new PrimaryDrawerItem().withName("Logout").withIcon(R.drawable.logout_icon).withIdentifier(7).withTypeface(face);


        final Drawer drawer = new DrawerBuilder()
                .withHeaderDivider(true)
                .withActivity(this)
                .withSliderBackgroundColor(getResources().getColor(android.R.color.white))
                .withTranslucentStatusBar(true)
                .withHeader(R.layout.header)
                .withActionBarDrawerToggle(false)
                .addDrawerItems(
                        home, played, charts, ledger, earn, account, rate, notice, deposit, withdraw, howto, transaction, share, logout
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        if (drawerItem.equals(1)) {
                            startActivity(new Intent(MainActivity.this, profile.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        }
                        if (drawerItem.equals(101)) {
                            startActivity(new Intent(MainActivity.this, chart_menu.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        }
                        if (drawerItem.equals(2)) {
                            startActivity(new Intent(MainActivity.this, rate.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        }
                        if (drawerItem.equals(21)) {
                            startActivity(new Intent(MainActivity.this, earn.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        }
                        if (drawerItem.equals(3)) {
                            startActivity(new Intent(MainActivity.this, notice.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        }
                        if (drawerItem.equals(4)) {
                            if (is_gateway.equals("1")){
                                startActivity(new Intent(MainActivity.this, deposit_money.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                            } else {
                                openWhatsApp();
                            }
                        }
                        if (drawerItem.equals(41)) {
                            openWhatsApp();
                        }
                        if (drawerItem.equals(10)) {
                            startActivity(new Intent(MainActivity.this, howot.class));
                        }
                        if (drawerItem.equals(11)) {

                            Intent sendIntent = new Intent();
                            sendIntent.setAction(Intent.ACTION_SEND);
                            sendIntent.putExtra(Intent.EXTRA_TEXT,
                                    "Download "+getString(R.string.app_name)+" and earn money at home, Download link - " + constant.link);
                            sendIntent.setType("text/plain");
                            startActivity(sendIntent);
                        }
                        if (drawerItem.equals(7)) {
                            preferences.edit().clear().apply();
                            Intent in = new Intent(getApplicationContext(), login.class);
                            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(in);
                            finish();
                        }
                        if (drawerItem.equals(6)) {
                            startActivity(new Intent(MainActivity.this, ledger.class));
                        }
                        if (drawerItem.equals(8)) {
                            startActivity(new Intent(MainActivity.this, transactions.class));
                        }
                        if (drawerItem.equals(9)) {
                            startActivity(new Intent(MainActivity.this, played.class));
                        }

                        return false;
                    }
                })
                .build();


        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen()) {
                    drawer.closeDrawer();
                } else {
                    drawer.openDrawer();
                }
            }
        });

    }


    private void apicall() {


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        final StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject1 = new JSONObject(response);

                            if (jsonObject1.getString("active").equals("0")) {
                                Toast.makeText(MainActivity.this, "Your account temporarily disabled by admin", Toast.LENGTH_SHORT).show();

                                preferences.edit().clear().apply();
                                Intent in = new Intent(getApplicationContext(), login.class);
                                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(in);
                                finish();
                            }

                            if (!jsonObject1.getString("session").equals(getSharedPreferences(constant.prefs, MODE_PRIVATE).getString("session", null))) {
                                Toast.makeText(MainActivity.this, "Session expired ! Please login again", Toast.LENGTH_SHORT).show();

                                preferences.edit().clear().apply();
                                Intent in = new Intent(getApplicationContext(), login.class);
                                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(in);
                                finish();
                            }

                            balance.setText(jsonObject1.getString("wallet"));

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                hometext.setText(Html.fromHtml(jsonObject1.getString("homeline"), Html.FROM_HTML_MODE_COMPACT));
                            } else {
                                hometext.setText(Html.fromHtml(jsonObject1.getString("homeline")));
                            }




                            ArrayList<String> name = new ArrayList<>();
                            ArrayList<String> result = new ArrayList<>();

                            JSONArray jsonArray = jsonObject1.getJSONArray("result");
                            for (int a = 0; a < jsonArray.length(); a++){
                                JSONObject jsonObject = jsonArray.getJSONObject(a);

                                name.add(jsonObject.getString("market"));
                                result.add(jsonObject.getString("result"));

                            }


                            adapter_result rc = new adapter_result(MainActivity.this,name,result);
                            recyclerview.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                            recyclerview.setAdapter(rc);
                            rc.notifyDataSetChanged();


                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("wallet", jsonObject1.getString("wallet")).apply();
                            editor.putString("homeline", jsonObject1.getString("homeline")).apply();
                            editor.putString("code", jsonObject1.getString("code")).apply();
                            is_gateway = jsonObject1.getString("gateway");


                        } catch (JSONException e) {
                            e.printStackTrace();


                            Toast.makeText(MainActivity.this, "Something went wrong !", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        error.printStackTrace();

                        Toast.makeText(MainActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("mobile", preferences.getString("mobile", null));
                params.put("session",getSharedPreferences(constant.prefs, MODE_PRIVATE).getString("session", null));

                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);
    }

    @Override
    protected void onResume() {
        apicall();
        super.onResume();
    }

    private void openWhatsApp() {
        String url = constant.whatsapplink;

        Uri uri = Uri.parse(url);
        Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(sendIntent);
    }



    private void initViews() {
        balance = findViewById(R.id.balance);
        hometext = findViewById(R.id.hometext);
        single = findViewById(R.id.single);
        jodi = findViewById(R.id.jodi);
        crossing = findViewById(R.id.crossing);
        singlepatti = findViewById(R.id.singlepatti);
        doublepatti = findViewById(R.id.doublepatti);
        tripepatti = findViewById(R.id.tripepatti);
        halfsangam = findViewById(R.id.halfsangam);
        fullsangam = findViewById(R.id.fullsangam);
        exit = findViewById(R.id.exit);
        logout = findViewById(R.id.logout);
        refresh = findViewById(R.id.refresh);
        supportno = findViewById(R.id.supportno);
        support = findViewById(R.id.support);
        scrollView = findViewById(R.id.scrollView);
        recyclerview = findViewById(R.id.recyclerview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.lang_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_lang_en:
                Locale locale = new Locale("en");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
              //  Toast.makeText(this, "Locale in English !", Toast.LENGTH_LONG).show();
                break;

            case R.id.menu_lang_hi:
                Locale locale2 = new Locale("pt");
                Locale.setDefault(locale2);
                Configuration config2 = new Configuration();
                config2.locale = locale2;
                getBaseContext().getResources().updateConfiguration(config2, getBaseContext().getResources().getDisplayMetrics());

               // Toast.makeText(this, "Locale in Portugal !", Toast.LENGTH_LONG).show();
                break;

            case R.id.menu_lang_mr:
                Locale locale3 = new Locale("es");
                Locale.setDefault(locale3);
                Configuration config3 = new Configuration();
                config3.locale = locale3;
                getBaseContext().getResources().updateConfiguration(config3, getBaseContext().getResources().getDisplayMetrics());

               // Toast.makeText(this, "Locale in Spain !", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
