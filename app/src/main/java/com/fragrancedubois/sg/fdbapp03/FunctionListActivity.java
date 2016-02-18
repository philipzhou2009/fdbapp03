package com.fragrancedubois.sg.fdbapp03;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class FunctionListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Utils.setLocale(this, "fr");

        super.onCreate(savedInstanceState);
        Utils.setWindowFullScreen(this);

        setContentView(R.layout.activity_function_list);

        Utils.enableAppbarWithBack(this);

        /*
        String label = null;
        try {
            label = getResources().getString(
                    getPackageManager().getActivityInfo(getComponentName(), 0).labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Log.e("FunctionListActivity", "Activity Label: " + label);
        */

        final Button btnWebsite = (Button) findViewById(R.id.btnWebsite);
        btnWebsite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSomeActivity(v, WebsiteActivity.class);
            }
        });

        final Button btnAbout = (Button) findViewById(R.id.btnAbout);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSomeActivity(v, AboutActivity.class);
            }
        });

        final Button btnConsultant = (Button) findViewById(R.id.btnConsultant);
        btnConsultant.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Utils.startSomeActivity(v, PersonalityActivity.class, );
                startSomeActivity(v, PersonalityActivity.class);
            }
        });

        final Button btnAppAbout = (Button) findViewById(R.id.btnAppAbout);
        btnAppAbout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSomeActivity(v, AppActivity.class);
            }
        });

        /*
        final Button btnGallery = (Button) findViewById(R.id.btnGallery);
        btnGallery.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSomeActivity(v, GalleryActivity.class);
            }
        });
        */
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
    */

    public void startSomeActivity(View v, Class c) {
        Intent intent = new Intent(v.getContext(), c);
        startActivity(intent);
    }
}
