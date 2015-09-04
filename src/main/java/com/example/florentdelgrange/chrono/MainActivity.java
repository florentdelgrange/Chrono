package com.example.florentdelgrange.chrono;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button chronoButton = (Button) findViewById(R.id.button);
        chronoButton.getLayoutParams().width = 150;
        chronoButton.getLayoutParams().height = 75;
        chronoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setContentView(R.layout.chrono);
                Intent intent = new Intent(MainActivity.this, ChronometerActivity.class);
                startActivity(intent);
            }
        });

    }

}