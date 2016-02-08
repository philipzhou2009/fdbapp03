package com.example.philip.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);



        final Button buttonFr = (Button) findViewById(R.id.buttonFr);
        buttonFr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setLocale("fr");
                startFunctionListActivity(v);
            }
        });

        final Button buttonEn = (Button) findViewById(R.id.buttonEn);
        buttonEn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setLocale("en");
                startFunctionListActivity(v);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setLocale(String sLocale) {
        Log.i("MyActivity", "setLocale=" + sLocale);

        Locale locale = new Locale(sLocale);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }

    public void startFunctionListActivity(View v) {
        Log.i("MyActivity", "startFunctionListActivity");

        Intent intent = new Intent(v.getContext(), FunctionListActivity.class);
        startActivity(intent);
    }
}
