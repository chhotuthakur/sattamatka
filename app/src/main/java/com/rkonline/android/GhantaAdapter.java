package com.rkonline.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mikepenz.fastadapter.listeners.OnClickListener;

import java.util.List;

public class GhantaAdapter extends RecyclerView.Adapter<GhantaAdapter.ProductViewHolder> {


    private Context mCtx;
    private List<GhantaModel> resList;

    public GhantaAdapter(Context mCtx, List<GhantaModel> productList) {
        this.mCtx = mCtx;
        this.resList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.ghanta_layout_one, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        GhantaModel product = resList.get(position);




        //holder.textResult.setText(product.get());
        holder.textTime.setText(product.getTime());
        holder.textTime2.setText(String.valueOf(product.getTime()));
//        holder.textViewPrice.setText(String.valueOf(product.getPrice()));
        //holder.textStatus.setText(product.getStatus());
        holder.stat = product.getStatus();
        if (holder.stat.toString() == "1"){
            holder.textStatus.setText("OPEN");
        }else{
            holder.textStatus.setText("CLOSE");

        }


    }

    @Override
    public int getItemCount() {
        return resList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        latobold textResult, textTime, textTime2, textStatus;
        String stat ;




        public ProductViewHolder(View itemView) {
            super(itemView);

           textResult = itemView.findViewById(R.id.result_market_one);
            textTime = itemView.findViewById(R.id.time_market_one);
            textTime2 = itemView.findViewById(R.id.time_market_two);
            textStatus = itemView.findViewById(R.id.ghanta_market_status);


        }
    }


}