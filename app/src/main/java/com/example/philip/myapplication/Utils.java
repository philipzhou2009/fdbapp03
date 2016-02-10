package com.example.philip.myapplication;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.util.Locale;

/**
 * Created by philip on 2/9/16.
 */
public class Utils {
    // locales
    static public void setLocale(Activity activity, String sLocale) {
        //Log.i("MyActivity", "setLocale=" + sLocale);

        Locale locale = new Locale(sLocale);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        activity.getBaseContext().getResources().updateConfiguration(config,
                activity.getBaseContext().getResources().getDisplayMetrics());
    }


    static public void setWindowFullScreenNoTitle(Activity activity) {
        // Make this activity, full screen
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Hide the Title bar of this activity screen
        activity.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    }

    static public void setWindowFullScreen(Activity activity) {
        // Make this activity, full screen
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    // app bar
    static public void enableAppbarWithBack(AppCompatActivity activity) {
        Toolbar myToolbar = (Toolbar) activity.findViewById(R.id.my_toolbar);
        activity.setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = activity.getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }
}
