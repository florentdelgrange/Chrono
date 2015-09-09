package com.example.florentdelgrange.chrono;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.example.florentdelgrange.chrono.appState.AppState;
import com.example.florentdelgrange.chrono.appState.MenuMode;

public class MainActivity extends AppCompatActivity {

    private AppState state;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws java.lang.NoSuchMethodError{
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        if(savedInstanceState != null && !savedInstanceState.isEmpty()){
            state = (AppState) savedInstanceState.getSerializable("laststate");
            Log.i("MY",state.debug());
            state.reload(this);
        }
        else {
            state = new MenuMode(this);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putSerializable("laststate", state);
        super.onSaveInstanceState(savedInstanceState);
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