package com.example.florentdelgrange.appState;

import android.annotation.TargetApi;
import android.os.Build;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.florentdelgrange.chrono.ChronometerTimer;
import com.example.florentdelgrange.chrono.MainActivity;
import com.example.florentdelgrange.chrono.R;

/**
 * Created by Flo on 06-09-15.
 */
public class ChronometerMode implements AppState {

    MainActivity mainActivity;

    public ChronometerMode(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        final ChronometerTimer timer = new ChronometerTimer((TextView) mainActivity.findViewById(R.id.textView));
        final ImageButton playButton = (ImageButton) mainActivity.findViewById(R.id.imageButton);
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
        final ImageButton stopButton = (ImageButton) mainActivity.findViewById(R.id.imageButton2);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.stop();
                playButton.setImageResource(R.drawable.playbutton);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onKeyDown() {
        Transition myTransition;
        //try catch because Slide dosen't work on older Android version than Lollipop
        try{
            myTransition = new Slide(Gravity.LEFT);
        }
        catch (java.lang.NoSuchMethodError exception){
            myTransition = new Fade();
        }
        ViewGroup mSceneRoot = (ViewGroup) mainActivity.findViewById(R.id.scene_root);
        Scene menuScene = Scene.getSceneForLayout(mSceneRoot, R.layout.activity_main, mainActivity);
        TransitionManager.go(menuScene, myTransition);
        mainActivity.setState(new MenuMode(mainActivity));
    }
}
