package com.lamakisa.assignment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.lamakisa.assignment.common.constants;


public class MainActivity extends ActionBarActivity {

    private ImageView USD;
    private ImageView EUR;
    private String name;
    private SharedPreferences sharedInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedInfo = getSharedPreferences(constants.SHARED_PREF, MODE_PRIVATE);

        USD = (ImageView) findViewById(R.id.USD);
        EUR = (ImageView) findViewById(R.id.EUR);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Home Page");
        actionBar.setDisplayHomeAsUpEnabled(true);

        //android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.CYAN));

        //actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("879f38")));

        USD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareToGo("USD");
            }
        });

        EUR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareToGo("EUR");
            }
        });


    }

    public void prepareToGo(String cur)
    {
        SharedPreferences.Editor editor = sharedInfo.edit();
        editor.putString(constants.NAMES_PREF, cur);
        editor.commit();

        Intent screen = new Intent(MainActivity.this, Activity2.class);
        startActivity(screen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        else if(id == android.R.id.home)
        {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle ("Do You want to quit")
                .setMessage("Really??")
                .setPositiveButton("okay", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        finish();
                    }
                })
                .setNegativeButton ("NO", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                    }
                }).show();
    }
}
