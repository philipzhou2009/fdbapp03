package com.example.philip.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
    public int mSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setWindowFullScreen(this);

        setContentView(R.layout.activity_selections);

        Utils.enableAppbarWithBack(this);


        ActionBar bar = getSupportActionBar();
        if (bar != null) {

            bar.setCustomView(getLayoutInflater().inflate(R.layout.appbar_personality, null),
                    new ActionBar.LayoutParams(
                            ActionBar.LayoutParams.MATCH_PARENT,
                            ActionBar.LayoutParams.WRAP_CONTENT,
                            Gravity.RIGHT
                    )
            );

            bar.setDisplayShowCustomEnabled(true);
        }

        //mViewConinue = (View) findViewById(R.id.selectioncontinue);

        try {
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


        ImageButton ib = (ImageButton) findViewById(R.id.imageButton);
        ib.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("imageButton", "go to color wheel");
                startColorWheel();
            }
        });

        updateAppbar();
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu_my, menu);
        return false;
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
    */

    public void updateAppbar() {
        int count = 3;
        for (FdbWheeler wheeler : mFdbWheelers) {
            if (wheeler.mFlag) {
                count--;
            }
        }

        TextView tv = (TextView) findViewById(R.id.barCounter);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) tv.getLayoutParams();
        ImageButton ib = (ImageButton) findViewById(R.id.imageButton);

        int iDp = 24;
        int iVi = View.INVISIBLE;
        if (count == 0) {
            iDp = 55;
            iVi = View.VISIBLE;
            tv.setText(R.string.go_to_colorwheel);
        } else {
            tv.setText("" + count);
        }

        params.rightMargin = Utils.convertDpToPx(this.getBaseContext(), iDp);
        ib.setVisibility(iVi);

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

    public void startColorWheel() {

        ArrayList<String> strList = new ArrayList();
        for (FdbWheeler wheeler : mFdbWheelers) {
            if (wheeler.mFlag == true) {
                strList.add(wheeler.mName);
                //Log.e("fdb", wheeler.mName);
            }
        }

        Intent myIntent = new Intent(this.getBaseContext(), ColorWheel.class);
        myIntent.putExtra("selections", strList);
        startActivityForResult(myIntent, 0);
    }
}

