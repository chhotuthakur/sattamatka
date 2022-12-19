package com.rkonline.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GhantaMenuActivity extends AppCompatActivity {
    String Timing;
//    Bundle timing ;
    LinearLayout ll1,ll2,ll3,ll4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghanta_menu);

        TextView textView = (TextView) findViewById(R.id.time_text);
        ll1 =(LinearLayout)findViewById(R.id.single_patti_ll);
        ll2 =(LinearLayout)findViewById(R.id.double_patti_ll);
        ll3 =(LinearLayout)findViewById(R.id.triple_patti_ll);
        ll4 =(LinearLayout)findViewById(R.id.triple_dhamaka_ll);

       Intent intent = getIntent();
       Timing = getIntent().getStringExtra("time");

       textView.setText(Timing);


    }
}