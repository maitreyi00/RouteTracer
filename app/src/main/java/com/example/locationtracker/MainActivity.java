package com.example.locationtracker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

     // Initaliaze variable
      EditText etsource,etdestination;
      Button btntrack,btnloc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        // Assign variable
        // btnloc=findViewById(R.id.current_location);
        etdestination = findViewById(R.id.et_destiny);
        btntrack = findViewById(R.id.bt_track);
        etsource = findViewById(R . id.et_source);

        btntrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get value from edittext
                String sSouce = etsource.getText().toString().trim();
                String dDestiny = etdestination.getText().toString().trim();


                // Check condition
                if(sSouce.equals("") && dDestiny.equals(""))
                {
                    // When both value blank
                    Toast.makeText(getApplicationContext(),"Enter Both Location",Toast.LENGTH_SHORT).show();
                }
                else
                    {
                     // when both value fill display track
                        DisplayTrack(sSouce,dDestiny);


                    }



            }
        });


        /*btnloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);

            }
        });*/


    }

    private void DisplayTrack(String sSouce, String dDestiny)
    {
        // if the device is not having google map redirect it to playstore

        try {
            //when google map installed
            // intialize url
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" +sSouce + "/" +dDestiny);

            //Intialize intent with action view

            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            // set pkg
            intent.setPackage("com.google.android.apps.maps");

            // set flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }catch (ActivityNotFoundException e)
        {
            // when google map is not install
            // set uri
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            // intialize intent with action view
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            // set flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // start acivity
            startActivity(intent);

        }


    }
}