package com.bms.yahoooauth2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

public class StartingActivity extends AppCompatActivity {

    WebView webView;
    ProgressBar pbBar;
    FrameLayout layer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        webView = (WebView) findViewById(R.id.webview);
        pbBar = (ProgressBar) findViewById(R.id.pbBar);
        layer = (FrameLayout) findViewById(R.id.layer);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pbBar.setVisibility(View.VISIBLE);
                layer.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
                System.out.println("url is" + url);
                if (url.startsWith(Constants.REDIRECT_URI)) {
                    Intent intent = new Intent();
                    String callback[] = url.split("\\?");
                    String code = callback[1].split("&")[0].split("=")[1];
                    intent.putExtra(Constants.CODE, code);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                pbBar.setVisibility(View.GONE);
                layer.setVisibility(View.GONE);
                super.onPageFinished(view, url);
                System.out.println("url is" + url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
        System.out.println("oauth uri" + Constants.OAuth_URI);
        webView.loadUrl(Constants.OAuth_URI);
    }
}
