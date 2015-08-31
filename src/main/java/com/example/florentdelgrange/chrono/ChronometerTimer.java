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
    private TextView chronoView;
    private Handler myHandler;
    private Runnable timerTask;


    /**
     * @param timeView the TextView where it will be written the chronometer
     */
    public ChronometerTimer(TextView timeView) {
        super();
        this.nanoTime = System.nanoTime();
        myHandler = new Handler();
        this.chronoView = timeView;
        this.timerTask = new Runnable() {
            @Override
            public void run() {
                long pos = (System.nanoTime() - nanoTime);

                StringBuilder stringBuilder = new StringBuilder(Long.toString(TimeUnit.NANOSECONDS.toMinutes(pos)));
                stringBuilder.append(':');
                String seconds = Long.toString(TimeUnit.NANOSECONDS.toSeconds(pos)%60);
                switch (seconds.length()){
                    case 1 :
                        stringBuilder.append("0");
                    default :
                        stringBuilder.append(seconds);
                        stringBuilder.append('.');
                        break;
                }
                String centisec = Long.toString(TimeUnit.NANOSECONDS.toMillis(pos)%1000);
                switch (centisec.length()){
                    case 1 :
                        stringBuilder.append("0");
                        stringBuilder.append(centisec.charAt(0));
                        break;
                    default :
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

    }

    public void run(){
        schedule(new TimerTask() {
            @Override
            public void run() {
                myHandler.post(timerTask);
            }
        },0,17 );
    }

}
