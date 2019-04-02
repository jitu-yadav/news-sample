package com.example.demo.news;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

public class NewsDetailsActivity extends AppCompatActivity {

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra("selectedNews")) {
            WebView mwebView = (WebView) findViewById(R.id.webview);

            pDialog = new ProgressDialog(this);
            // Showing progress dialog before making http request
            pDialog.setMessage("Loading...");

            mwebView.getSettings().setAppCacheEnabled(true);

            mwebView.setWebViewClient(new WebViewClient());

            mwebView.loadUrl(intent.getStringExtra("selectedNews"));
        } else {
            Toast.makeText(NewsDetailsActivity.this, "something went wrong!!!", Toast.LENGTH_LONG).show();
        }
    }

    public class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            pDialog.show();
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            /*** Hide ProgressBar while page completely load ***/
            pDialog.dismiss();

        }

    }
}
