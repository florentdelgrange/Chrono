package com.example.florentdelgrange.chrono;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.florentdelgrange.appState.AppState;
import com.example.florentdelgrange.appState.MenuMode;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    AppState state;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws java.lang.NoSuchMethodError{
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        state = new MenuMode(this);
    }

    public void setState(AppState state){
        this.state = state;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            state.onKeyDown();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}