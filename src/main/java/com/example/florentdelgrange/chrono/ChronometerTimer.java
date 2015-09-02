package com.example.florentdelgrange.chrono;

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

    public TextView getChronoView() {
        return chronoView;
    }

    private TextView chronoView;
    private Handler myHandler;
    private Runnable timerTask;
    private TimerTask scheduleTask;


    public enum ChronoState {RUN, PAUSE}

    ;
    private ChronoState state;


    /**
     * @param timeView the TextView where it will be written the chronometer
     */
    public ChronometerTimer(TextView timeView) {
        super();
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
        state = ChronoState.PAUSE;

    }

    /**
     * Run the chronometer :
     * update the textView to display the time
     */
    public void run() {
        breakTime += System.nanoTime() - nanoPauseTime;
        scheduleTask = new TimerTask() {
            @Override
            public void run() {
                myHandler.post(timerTask);
            }
        };
        schedule(scheduleTask, 0, 17);
        state = ChronoState.RUN;
    }

    /**
     * put the chronometer in pause mode
     */
    public void pause() {
        state = ChronoState.PAUSE;
        scheduleTask.cancel();
        purge();
        nanoPauseTime = System.nanoTime();
    }

    /**
     * get the chronometer state (in pause or running)
     * @return the state of the chronometer
     */
    public ChronoState getState() {
        return state;
    }

}