package com.fragrancedubois.sg.fdbapp03;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;
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

    public static int getResId(String resName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // UI
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

        String label = getActivityLabel(activity);
        if (label != null) {
            ab.setTitle(label);
        }


    }

    // tools
    static public void startSomeActivity(View v, Class c, Activity activity) {
        Intent intent = new Intent(v.getContext(), c);
        activity.startActivity(intent);
    }

    // dp to px
    static public int convertDpToPx(Context mContext, int iDp) {
        Resources r = mContext.getResources();
        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                iDp,
                r.getDisplayMetrics()
        );

        return px;
    }

    static public int convertPxToDp(Context mContext, int iPx) {
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        int iDp = (int) (iPx / displayMetrics.density);

        return iDp;
    }

    /*
    http://stackoverflow.com/questions/1457803/how-do-i-access-androidlabel-for-an-activity
     */
    static public String getActivityLabel(Activity activity) {
        String label = null;
        try {
            label = activity.getResources().getString(
                    activity.getPackageManager().getActivityInfo(
                            activity.getComponentName(), 0).labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Log.d("Utils", "Activity Label: " + label);

        return label;
    }

    static public boolean getNetworkConnection(Activity activity) {
        Context context = activity.getBaseContext();

        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }
}
