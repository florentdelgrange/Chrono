package com.example.florentdelgrange.chrono;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Flo on 04-09-15.
 */
public class ChronometerActivity extends Activity {

    private ChronometerTimer timer;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        timer = new ChronometerTimer((TextView) findViewById(R.id.textView));
        final ImageButton playButton = (ImageButton) findViewById(R.id.imageButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (timer.getState()) {
                    case RUN:
                        timer.pause();
                        playButton.setImageResource(R.drawable.playbutton);
                        break;
                    default:
                        timer.run();
                        playButton.setImageResource(R.drawable.pausebutton);
                        break;
                }
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
