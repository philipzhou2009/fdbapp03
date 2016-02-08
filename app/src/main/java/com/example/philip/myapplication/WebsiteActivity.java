package com.example.philip.myapplication;

import android.os.Bundle;
import android.app.Activity;
import android.webkit.WebView;

public class WebsiteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);

        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("http://fragrancedubois.com");
    }

}
