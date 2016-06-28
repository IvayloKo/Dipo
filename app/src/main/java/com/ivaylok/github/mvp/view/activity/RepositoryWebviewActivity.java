package com.ivaylok.github.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import com.ivaylok.github.R;

public class RepositoryWebviewActivity extends AppCompatActivity {

    private WebView mWebViewCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_webview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String mUrl = intent.getStringExtra(RepoActivity.CODE_URL);

        mWebViewCode = (WebView) findViewById(R.id.webViewRepositoryCode);
        mWebViewCode.loadUrl(mUrl);
    }

}
