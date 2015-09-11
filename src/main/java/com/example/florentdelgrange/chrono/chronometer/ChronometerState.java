package com.example.florentdelgrange.chrono.chronometer;

import android.widget.ImageButton;

/**
 * Created by Flo on 06-09-15.
 */
public interface ChronometerState {

    /**
     * Called when the user touch the play button.
     * Changes with the chronometer state.
     */
    void playClick();

    void reload(ImageButton playButton);

}
