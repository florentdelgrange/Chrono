package com.example.florentdelgrange.chrono.chronometer;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import android.os.Handler;

/**
 * Created by Flo on 30-08-15.
 */
public class ChronometerTimer extends Timer {

    private long nanoTime;
    private long nanoPauseTime;
    private long breakTime;
    private TextView chronoView;
    private Handler myHandler;
    private Runnable timerTask;
    private TimerTask scheduleTask;
    private boolean init;
    private ImageButton playButton;

    private ChronometerState state;


    /**
     * @param timeView the TextView where it will be written the chronometer
     */
    public ChronometerTimer(TextView timeView, ImageButton playButton) {
        super();
        this.playButton = playButton;
        init = false;
        this.breakTime = 0;
        this.nanoPauseTime = System.nanoTime();
        this.nanoTime = System.nanoTime();
        myHandler = new Handler();
        this.chronoView = timeView;
        this.timerTask = new Runnable() {
            @Override
            public void run() {
                long pos = (System.nanoTime() - nanoTime) - breakTime;

                StringBuilder stringBuilder = new StringBuilder(Long.toString(TimeUnit.NANOSECONDS.toMinutes(pos)));
                stringBuilder.append(':');
                String seconds = Long.toString(TimeUnit.NANOSECONDS.toSeconds(pos) % 60);
                switch (seconds.length()) {
                    case 1:
                        stringBuilder.append("0");
                    default:
                        stringBuilder.append(seconds);
                        stringBuilder.append('.');
                        break;
                }
                String centisec = Long.toString(TimeUnit.NANOSECONDS.toMillis(pos) % 1000);
                switch (centisec.length()) {
                    case 1:
                        stringBuilder.append("0");
                        stringBuilder.append(centisec.charAt(0));
                        break;
                    default:
                        stringBuilder.append(centisec.charAt(0));
                        stringBuilder.append(centisec.charAt(1));
                        break;
                }

                String time = stringBuilder.substring(0);
                chronoView.setText(time);
                chronoView.setTextSize(80);
                chronoView.invalidate();
            }
        };
        state = new InPause(this, playButton);

    }

    /**
     * Run the chronometer :
     * update the textView to display the time
     */
    public void run() {
        init = true;
        breakTime += System.nanoTime() - nanoPauseTime;
        scheduleTask = new TimerTask() {
            @Override
            public void run() {
                myHandler.post(timerTask);
            }
        };
        schedule(scheduleTask, 0, 17);
        state = new Running(this, playButton);
    }

    /**
     * put the chronometer in pause mode
     */
    public void pause() {
        scheduleTask.cancel();
        purge();
        nanoPauseTime = System.nanoTime();
        state = new InPause(this, playButton);
    }

    public void stop(){
        if(init) {
            this.breakTime = 0;
            this.nanoPauseTime = System.nanoTime();
            this.nanoTime = System.nanoTime();
            scheduleTask.cancel();
            purge();
            chronoView.setText("0:00:00");
            chronoView.invalidate();
        }
        state = new InPause(this, playButton);
    }

    /**
     * get the chronometer state (in pause or running)
     * @return the state of the chronometer
     */
    public ChronometerState getState() {
        return state;
    }


}