package com.fragrancedubois.sg.fdbapp03;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class WebsiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setWindowFullScreen(this);

        setContentView(R.layout.activity_website);

        Utils.enableAppbarWithBack(this);

        WebView webview = (WebView) findViewById(R.id.webview);
        LinearLayout warning = (LinearLayout) findViewById(R.id.networkwarninglayout);

        if(!Utils.getNetworkConnection(this))
        {
            webview.setVisibility(View.INVISIBLE);
            warning.setVisibility(View.VISIBLE);
            return;
        }
        else
        {
            warning.setVisibility(View.INVISIBLE);
            webview.setVisibility(View.VISIBLE);
        }


        // Let's display the progress in the activity title bar, like the
        // browser app does.
        //getWindow().requestFeature(Window.FEATURE_PROGRESS);

        webview.getSettings().setJavaScriptEnabled(true);

        final Activity activity = this;
        webview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                // Activities and WebViews measure progress with different scales.
                // The progress meter will automatically disappear when we reach 100%
                activity.setProgress(progress * 1000);
            }
        });
        webview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }
        });


        webview.loadUrl("http://fragrancedubois.com");
    }

}
