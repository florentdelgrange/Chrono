package com.example.florentdelgrange.chrono.appState;

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

import com.example.florentdelgrange.chrono.chronometer.ChronometerTimer;
import com.example.florentdelgrange.chrono.MainActivity;
import com.example.florentdelgrange.chrono.R;

/**
 * Created by Flo on 06-09-15.
 */
public class ChronometerMode implements AppState {

    private MainActivity mainActivity;
    private ChronometerTimer timer;

    public ChronometerMode(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        init();
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

    @Override
    public void init() {
        final ImageButton playButton = (ImageButton) mainActivity.findViewById(R.id.imageButton);
        if(timer == null) {
            timer = new ChronometerTimer((TextView) mainActivity.findViewById(R.id.textView), playButton);
        }
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.getState().playClick();
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

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void reload(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        final ViewGroup mSceneRoot = (ViewGroup) mainActivity.findViewById(R.id.scene_root);
        Scene chronoScene = Scene.getSceneForLayout(mSceneRoot, R.layout.chrono, mainActivity);
        TransitionManager.go(chronoScene);
        init();
    }

    @Override
    public String debug() {
        return ">>> CHRONO MODE";
    }
}
