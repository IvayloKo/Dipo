package com.ivaylok.github.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ivaylok.github.R;

public class RepoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);

        Intent repoIntent = getIntent();
        String mRepoName = repoIntent.getStringExtra(GithubActivity.EXTRA_MESSAGE);

        TextView mTextViewRepoName = (TextView) findViewById(R.id.tvRepoName);
        mTextViewRepoName.setText(mRepoName);
    }
}
