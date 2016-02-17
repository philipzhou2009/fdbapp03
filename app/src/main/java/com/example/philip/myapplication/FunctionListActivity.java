package com.example.philip.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class FunctionListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setWindowFullScreen(this);

        setContentView(R.layout.activity_function_list);

        Utils.enableAppbarWithBack(this);

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

        final Button btnConsultant = (Button) findViewById(R.id.btnConsultant);
        btnConsultant.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Utils.startSomeActivity(v, PersonalityActivity.class, );
                startSomeActivity(v, PersonalityActivity.class);
            }
        });

        final Button btnGallery = (Button) findViewById(R.id.btnGallery);
        btnGallery.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSomeActivity(v, GalleryActivity.class);
            }
        });
    }

    /*
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
    */

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

    public void startSomeActivity(View v, Class c) {
        Intent intent = new Intent(v.getContext(), c);
        startActivity(intent);
    }
}
