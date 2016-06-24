package com.ivaylok.github.mvp.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ivaylok.github.R;
import com.ivaylok.github.mvp.view.fragment.RepositoriesFragment;

public class RepoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);

        Intent repoIntent = getIntent();
        String mRepoName = repoIntent.getStringExtra(RepositoriesFragment.EXTRA_MESSAGE);

        TextView mTextViewRepoName = (TextView) findViewById(R.id.tvRepoName);
        mTextViewRepoName.setText(mRepoName);
    }
}
