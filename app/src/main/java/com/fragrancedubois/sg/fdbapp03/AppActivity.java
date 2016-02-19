package com.fragrancedubois.sg.fdbapp03;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

public class AppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Utils.setWindowFullScreen(this);

        setContentView(R.layout.activity_app);

        Utils.enableAppbarWithBack(this);

        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        */

        String build = getBaseContext().getString(R.string.build);
        Date buildDate = new Date(BuildConfig.TIMESTAMP);
        String sBuildDate = build +  ": " + buildDate;
        TextView tv1 = (TextView) findViewById(R.id.builddate);
        tv1.setText(sBuildDate);

        //Log.e("AppActivity", sBuildDate);

        String version = getBaseContext().getString(R.string.version);
        String vname = BuildConfig.VERSION_NAME;
        TextView tv2 = (TextView) findViewById(R.id.versionname);
        String sVersion = version + ": " + vname;
        tv2.setText(sVersion);


    }

}
