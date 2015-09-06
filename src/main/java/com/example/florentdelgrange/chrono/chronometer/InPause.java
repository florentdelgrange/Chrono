package com.example.florentdelgrange.chrono.chronometer;

import android.widget.ImageButton;

import com.example.florentdelgrange.chrono.R;

/**
 * Created by Flo on 06-09-15.
 */
public class InPause implements ChronometerState {

    private ChronometerTimer timer;
    private ImageButton playButton;

    public InPause(ChronometerTimer timer, ImageButton playButton){
        this.timer = timer;
        this.playButton = playButton;
    }

    @Override
    public void playClick() {
        timer.run();
        playButton.setImageResource(R.drawable.pausebutton);
    }
}
