package com.example.philip.myapplication;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by philip on 2/9/16.
 */
public class Utils {

    static public void setWindowFullScreenNoTitle(Activity activity)
    {
        // Make this activity, full screen
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Hide the Title bar of this activity screen
        activity.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    }

    static public void setWindowFullScreen(Activity activity)
    {
        // Make this activity, full screen
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }
}
