package com.example.florentdelgrange.chrono.appState;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.florentdelgrange.chrono.MainActivity;
import com.example.florentdelgrange.chrono.R;

/**
 * Created by Flo on 06-09-15.
 */
public class MenuMode implements AppState {

    private MainActivity mainActivity;

    public MenuMode(final MainActivity mainActivity){
        this.mainActivity = mainActivity;
        init();
    }

    @Override
    public void onKeyDown() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mainActivity.startActivity(intent);
    }

    @Override
    public void init() {
        final ViewGroup mSceneRoot = (ViewGroup) mainActivity.findViewById(R.id.scene_root);

        Button chronoButton = (Button) mainActivity.findViewById(R.id.button);

        chronoButton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Scene chronoScene = Scene.getSceneForLayout(mSceneRoot, R.layout.chrono, mainActivity);
                Transition myTransition;
                //try catch because Slide dosen't work on older Android version than Lollipop
                try {
                    myTransition = new Slide(Gravity.RIGHT);
                } catch (java.lang.NoSuchMethodError exception) {
                    myTransition = new Fade();
                }
                TransitionManager.go(chronoScene, myTransition);
                //Intent intent = new Intent(MainActivity.this, ChronometerActivity.class);
                //startActivity(intent);
                mainActivity.setState(new ChronometerMode(mainActivity));
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void reload(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        final ViewGroup mSceneRoot = (ViewGroup) mainActivity.findViewById(R.id.scene_root);
        Scene menuScene = Scene.getSceneForLayout(mSceneRoot, R.layout.activity_main, mainActivity);
        TransitionManager.go(menuScene);
        init();
    }

    @Override
    public String debug() {
        return ">>> MENU MODE";
    }
}
