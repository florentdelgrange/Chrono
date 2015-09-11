package com.example.florentdelgrange.chrono.chronometer;

import android.widget.ImageButton;

import com.example.florentdelgrange.chrono.R;

import java.util.TimerTask;

/**
 * Created by Flo on 06-09-15.
 */
public class Running implements ChronometerState {

    ChronometerTimer timer;
    ImageButton playButton;

    public Running(ChronometerTimer timer, ImageButton playButton){
        this.timer = timer;
        this.playButton = playButton;
    }

    @Override
    public void playClick() {
        timer.pause();
        playButton.setImageResource(R.drawable.playbutton);
    }

    @Override
    public void reload(ImageButton playButton) {
        this.playButton = playButton;
        playButton.setImageResource(R.drawable.pausebutton);
    }
}
