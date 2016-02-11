package com.example.philip.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PersonalityActivity extends AppCompatActivity {

    public List<FdbWheeler> mFdbWheelers;
    public Button mShowCW;
    public View mViewConinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setWindowFullScreen(this);

        setContentView(R.layout.activity_selections);

        Utils.enableAppbarWithBack(this);

        ActionBar bar = getSupportActionBar();
        if(bar != null) {

            bar.setCustomView(getLayoutInflater().inflate(R.layout.appbar_personality, null),
                    new ActionBar.LayoutParams(
                            ActionBar.LayoutParams.MATCH_PARENT,
                            ActionBar.LayoutParams.WRAP_CONTENT,
                            Gravity.RIGHT
                    )
            );

            bar.setDisplayShowCustomEnabled(true);
        }

        /*
        ImageButton ib = (ImageButton) findViewById(R.id.imageButton);
        ib.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("imageButton", "go to color wheel");
            }
        });
        */

        mViewConinue = (View) findViewById(R.id.selectioncontinue);

        try {
            //List<PerfumeXmlParser.Entry> entries = loadPerfumeXml();
            //mFdbWheelers = loadPersonalityXml();
            mFdbWheelers = FdbHelper.loadPersonalityXml(this);
        } catch (IOException e) {
            Log.e("PersonalityActivity", e.getMessage());
            //Log.e("FDB", "io error, failed to load Xml");
        } catch (XmlPullParserException e) {
            //Log.e("PersonalityActivity", "XmlPullParserException error: failed to parse xml");
            Log.e("PersonalityActivity", e.getMessage());
        }

        Collections.sort(mFdbWheelers);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this, mFdbWheelers));
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        /*
        if (id == R.id.action_settings) {
            return true;
        }
        */
        return super.onOptionsItemSelected(item);
    }

    public void showColorWheelButton() {
        int iCount = 0;
        for (FdbWheeler wheeler : mFdbWheelers) {
            if (wheeler.mFlag == true) {
                iCount++;
            }
        }

        if (iCount == 3) {
            mViewConinue.setVisibility(View.VISIBLE);
        } else {
            mViewConinue.setVisibility(View.INVISIBLE);
        }
    }

    public void updateAppbar() {
        ActionBar actionBar = this.getSupportActionBar();

        int count = 0;
        for (FdbWheeler wheeler : mFdbWheelers) {

            if (wheeler.mFlag) {
                count++;
            }

        }

        TextView tv = (TextView) findViewById(R.id.barCounter);
        if (tv != null) {
            tv.setText("" + count);

            if(count == 3)
            {
                tv.setVisibility(View.GONE);
                ImageButton ib = (ImageButton) findViewById(R.id.imageButton);
                ib.setVisibility(View.VISIBLE);
            }
            else
            {

            }
        }


    }

    public void startColorWheel(View view) {

        ArrayList<String> strList = new ArrayList();
        for (FdbWheeler wheeler : mFdbWheelers) {
            if (wheeler.mFlag == true) {
                strList.add(wheeler.mName);
                //Log.e("fdb", wheeler.mName);
            }
        }

        Intent myIntent = new Intent(view.getContext(), ColorWheel.class);
        myIntent.putExtra("selections", strList);
        startActivityForResult(myIntent, 0);
    }
}

