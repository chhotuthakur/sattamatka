package com.rkonline.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GhantaListActivity extends AppCompatActivity {


    ListView listViewg;
    List<GhantaModel>ghantaModelList;
    public final String API_URL = "https://rkonlinematka.in/api/ghantashow.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghanta_list2);
        listViewg =(ListView) findViewById(R.id.listViewGhanta);
        ghantaModelList = new ArrayList<>();

        loadData();
    }

    private void loadData() {
        RequestQueue mreq = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest()
    }


}