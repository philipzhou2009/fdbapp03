package com.fragrancedubois.sg.fdbapp03;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Utils.setWindowFullScreen(this);

        setContentView(R.layout.activity_about);

        Utils.enableAppbarWithBack(this);
    }

}
