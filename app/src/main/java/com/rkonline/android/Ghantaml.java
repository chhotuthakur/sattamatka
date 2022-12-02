package com.rkonline.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Ghantaml extends AppCompatActivity  {
    ListView listView ;
    GhantaMlAdapter ghantaMlAdapter;
    ArrayList<Ghanta>ghantaArrayList = new ArrayList<>();
    String Ghanta_URL = "https://rkonlinematka.in/api/ghantashow.php";
    Ghanta ghanta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghantaml);
        listView =(ListView) findViewById(R.id.gmarket_list);
        ghantaMlAdapter = new GhantaMlAdapter(this,ghantaArrayList);
        listView.setAdapter(ghantaMlAdapter);

        getData();
    }

    public void getData() {
        StringRequest request = new StringRequest(Request.Method.POST, Ghanta_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                ghantaArrayList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    if (success.equals("1")){
                        for (int i=0; i<jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            String time = object.getString("times");
                            String status = object.getString("status");

                            ghanta = new Ghanta(time,status);
                            ghantaArrayList.add(ghanta);
                            ghantaMlAdapter.notifyDataSetChanged();
                        }
                    }

                }catch (JSONException e){

                    e.printStackTrace();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Ghantaml.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

}