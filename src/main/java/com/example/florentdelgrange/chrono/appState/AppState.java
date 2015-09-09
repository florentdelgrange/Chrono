package com.example.florentdelgrange.chrono.appState;

import android.app.Activity;
import android.transition.Scene;

import com.example.florentdelgrange.chrono.MainActivity;

import java.io.Serializable;

/**
 * Created by Flo on 06-09-15.
 */
public interface AppState extends Serializable {

    /**
     * Called when the keyDown is pressed.
     * The action changes with the state.
     */
    void onKeyDown();

    /**
     * Called when the main activity is initialised or resumed.
     * Init the scene.
     */
    void init();

    /**
     * Called when the main activity is resumed.
     * Reload the scene.
     */
    void reload(MainActivity mainActivity);

    String debug();
}
