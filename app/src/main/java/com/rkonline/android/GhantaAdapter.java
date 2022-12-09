package com.rkonline.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class GhantaAdapter extends ArrayAdapter<GhantaModel> {


    private Context context;



    public GhantaAdapter(@NonNull Context context,ArrayList<GhantaModel>ghantaModelList ) {
        super(context,R.layout.ghanta_layout_one,ghantaModelList);

        this.context = context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        //creating a view with our xml layout
        View view = inflater.inflate(R.layout.ghanta_layout_one, null, true);
        //getting text views
        latobold tTimes = view.findViewById(R.id.time_market_one);
        latobold tStatus = view.findViewById(R.id.ghanta_market_status);
        //Getting the superHero for the specified position
        GhantaModel ghantaModel = getItem(position);
        //setting superHero values to textviews
        tTimes.setText(ghantaModel.getTimes());
        tStatus.setText(ghantaModel.getStatuss());
        //returning the listitem
        return view;
    }
}
