package com.example.philip.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class FunctionListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_list);

        final Button btnWebsite = (Button) findViewById(R.id.btnWebsite);
        btnWebsite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startWebsiteActivity(v);
            }
        });
    }

    public void startWebsiteActivity(View v) {
        Log.i("FunctionListActivity", "startWebsiteActivity");

        Intent intent = new Intent(v.getContext(), WebsiteActivity.class);
        startActivity(intent);
    }
}
