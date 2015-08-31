package com.example.florentdelgrange.chrono;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static MainActivity MAINACTIVITY;
    private long nanoTime;
    private long pauseTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button);
        button.getLayoutParams().width = 150;
        button.getLayoutParams().height = 75;


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setContentView(R.layout.chrono);
                nanoTime = System.nanoTime();
                ChronometterTimer timer = new ChronometterTimer((TextView)findViewById(R.id.textView));
                timer.run();
            }
        });
    }

    public MainActivity getMAINACTIVITY(){
        return MAINACTIVITY;
    }

    public void setNanoTime(long nanoTime){
        this.nanoTime = nanoTime;
    }

    public long getNanoTime(){
        return nanoTime;
    }

}