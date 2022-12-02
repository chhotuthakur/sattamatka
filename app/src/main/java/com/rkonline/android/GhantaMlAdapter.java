package com.rkonline.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class GhantaMlAdapter extends ArrayAdapter<Ghanta> {


    Context mctx;
    ListView mlistView;
    List<Ghanta> ghantaList;

    public GhantaMlAdapter(@NonNull Context mctx, List<Ghanta> ghantaList){

        super(mctx, R.layout.ghanta_layout_one, ghantaList);
        this.mctx = mctx;
        this.ghantaList = ghantaList;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        latobold textResult, textTime, textTime2, textStatus;
        String stat ;

//        attaching view

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ghanta_layout_one,null,true);

//        initializing variables

        textResult = v.findViewById(R.id.result_market_one);
        textTime = v.findViewById(R.id.time_market_one);
        textTime2 = v.findViewById(R.id.time_market_two);
        textStatus = v.findViewById(R.id.ghanta_market_status);



        //attaching data to widgets
        textTime.setText(ghantaList.get(position).getTimes());
        textTime2.setText(ghantaList.get(position).getTimes());
//        textResult.setText(ghantaList.get(position).get);
        textStatus.setText(ghantaList.get(position).getStatus());


        return super.getView(position, convertView, parent);
    }
}
