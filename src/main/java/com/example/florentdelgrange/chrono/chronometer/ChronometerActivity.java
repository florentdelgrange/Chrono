package com.example.florentdelgrange.chrono.chronometer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.florentdelgrange.chrono.R;

/**
 * Created by Flo on 04-09-15.
 * USELESS
 */
public class ChronometerActivity extends Activity {

    private ChronometerTimer timer;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        final ImageButton playButton = (ImageButton) findViewById(R.id.imageButton);
        timer = new ChronometerTimer((TextView) findViewById(R.id.textView), playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.getState().playClick();
            }
        });
        final ImageButton stopButton = (ImageButton) findViewById(R.id.imageButton2);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.stop();
                playButton.setImageResource(R.drawable.playbutton);
            }
        });
    }

}
