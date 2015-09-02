package com.example.florentdelgrange.chrono;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ChronometerTimer timer;

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
                setContentView(R.layout.chrono);
                timer = new ChronometerTimer((TextView) findViewById(R.id.textView));
                final ImageButton playButton = (ImageButton) findViewById(R.id.imageButton);
                playButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (timer.getState()){
                            case RUN:
                                timer.pause();
                                playButton.setImageResource(R.drawable.playbutton);
                                break;
                            case PAUSE:
                                timer.run();
                                playButton.setImageResource(R.drawable.pausebutton);
                                break;
                            default:
                                break;
                        }
                    }
                });
            }
        });
    }

}