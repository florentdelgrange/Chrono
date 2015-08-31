package com.example.florentdelgrange.chrono;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

/**
 * Created by florentdelgrange on 11/08/15.
 */
public class MainActivity2 extends Activity {
    public static int WINDOWSIZE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        final RelativeLayout layout = new RelativeLayout(this);
        layout.post(new Runnable() {
            public void run() {
                //inspired of http://www.codeproject.com/Tips/313848/Get-actual-screen-size-for-the-application-layout
                Rect rect = new Rect();
                Window win = getWindow();  // Get the Window
                win.getDecorView().getWindowVisibleDisplayFrame(rect);
                // Get the height of Status Bar
                int statusBarHeight = rect.top;
                // Get the height occupied by the decoration contents
                int contentViewTop = win.findViewById(Window.ID_ANDROID_CONTENT).getTop();
                // Calculate titleBarHeight by deducting statusBarHeight from contentViewTop
                int titleBarHeight = contentViewTop - statusBarHeight;
                Log.i("MY", "titleHeight = " + titleBarHeight + " statusHeight = " + statusBarHeight + " contentViewTop = " + contentViewTop);

                // By now we got the height of titleBar & statusBar
                // Now lets get the screen size
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                int screenHeight = metrics.heightPixels;
                int screenWidth = metrics.widthPixels;
                Log.i("MY", "Actual Screen Height = " + screenHeight + " Width = " + screenWidth);

                // Now calculate the height that our layout can be set
                // If you know that your application doesn't have statusBar added, then don't add here also. Same applies to application bar also
                int layoutHeight = screenHeight - (titleBarHeight + statusBarHeight);
                Log.i("MY", "Layout Height = " + layoutHeight);

                // Lastly, set the height of the layout
                ViewGroup.LayoutParams rootParams = layout.getLayoutParams();
                rootParams.height = layoutHeight;
                layout.setLayoutParams(rootParams);

                setWindowSize(titleBarHeight + statusBarHeight);

            }
        });

        setContentView(layout);

    }

    public void setWindowSize(int width){
        this.WINDOWSIZE = width;
    }

}
