package com.example.philip.myapplication;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class FunctionListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setWindowFullScreen(this);


        ActionBar actionBar = getActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            //actionBar.set
        }

        setContentView(R.layout.activity_function_list);

        final Button btnWebsite = (Button) findViewById(R.id.btnWebsite);
        btnWebsite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startWebsiteActivity(v);
            }
        });

        final Button btnAbout = (Button) findViewById(R.id.btnAbout);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startAboutActivity(v);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void startWebsiteActivity(View v) {
        Log.i("FunctionListActivity", "startWebsiteActivity");

        Intent intent = new Intent(v.getContext(), WebsiteActivity.class);
        startActivity(intent);
    }

    public void startAboutActivity(View v) {
        Log.i("MyActivity", "startAboutActivity");

        Intent intent = new Intent(v.getContext(), AboutActivity.class);
        startActivity(intent);
    }
}
