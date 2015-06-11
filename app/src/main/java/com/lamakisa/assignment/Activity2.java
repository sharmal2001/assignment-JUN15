package com.lamakisa.assignment;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lamakisa.assignment.common.constants;

import org.json.JSONObject;


public class Activity2 extends ActionBarActivity{

    private SharedPreferences sharedInfo;
    private String name, name2;
    private TextView resultView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);

        sharedInfo = getSharedPreferences(constants.SHARED_PREF, MODE_PRIVATE);
        name = sharedInfo.getString(constants.NAMES_PREF, constants.DEFAULT_NAME);

        resultView = (TextView) findViewById(R.id.resultTextView);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar(); // This is to put settitle
        actionBar.setTitle("Currency Converter");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.GREEN));


        System.out.println("user clicked " + name);

        //String url = "http://currency-api.appspot.com/api/CAD/USD.json?key=e3f730c5da6084137fadc0a1eb4f0fa1016c61b6";
        String url = "http://currency-api.appspot.com/api/CAD/" + name + ".json?key=e3f730c5da6084137fadc0a1eb4f0fa1016c61b6";


        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                System.out.println("The response is " + response);


                try {

                    JSONObject jobj = new JSONObject(response);

                    System.out.println("parsing");

                    System.out.println("source is " + jobj.getString("source"));
                    System.out.println("target is " + jobj.getString("target"));
                    System.out.println("amount is " + jobj.getString("amount"));

                    name2 = "$1.00 CAD = " + jobj.getString("amount") + " " + jobj.getString("target");
                    resultView.setText(name2);
                }
                catch(Exception e)
                {
                    System.out.println("something went wrong " + e.toString());
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_activity2, menu);
        getMenuInflater().inflate(R.menu.menu_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == android.R.id.home)
        {

        }

        return super.onOptionsItemSelected(item);
    }
}
