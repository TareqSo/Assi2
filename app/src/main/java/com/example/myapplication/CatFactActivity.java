package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CatFactActivity extends AppCompatActivity {

    private RequestQueue queue;
    private TextView textCatFact;
    private TextView textTime;
    private Button buttonGetCatFact;

    private Button buttonTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_fact);

        queue = Volley.newRequestQueue(this);

        textCatFact = findViewById(R.id.textCatFact);
        textTime = findViewById(R.id.textTime);


        buttonGetCatFact = findViewById(R.id.buttonGetCatFact);
        buttonTime = findViewById(R.id.buttonTime);

        buttonGetCatFact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRandomCatFact();
            }
        });
        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View c) {
                getRandomCatFact2();
            }
        });

    }

    private void getRandomCatFact() {
        String url = "https://catfact.ninja/fact";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    String fact = response.getString("fact");
                    int length = response.getInt("length");

                    String catFactText = "Fact: " + fact + "\nLength: " + length;
                    textCatFact.setText(catFactText);

                } catch (JSONException exception) {
                    Log.e("JSON Error", exception.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley Error", error.toString());
            }
        });

        queue.add(request);
    }
    private void getRandomCatFact2() {
        String url = "https://worldtimeapi.org/api/timezone/Asia/Hebron";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    String fact = response.getString("datetime");

                    String catFactText = "Date time: " + fact ;
                    textTime.setText(catFactText);

                } catch (JSONException exception) {
                    Log.e("JSON Error", exception.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley Error", error.toString());
            }
        });

        queue.add(request);
    }




}
