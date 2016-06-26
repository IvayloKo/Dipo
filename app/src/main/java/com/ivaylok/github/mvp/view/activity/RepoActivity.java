package com.ivaylok.github.mvp.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ivaylok.github.R;
import com.ivaylok.github.application.GithubApplication;
import com.ivaylok.github.mvp.model.SingleRepoResponse;
import com.ivaylok.github.mvp.presenter.SingleRepoPresenter;
import com.ivaylok.github.mvp.view.adapter.SingleRepoAdapter;
import com.ivaylok.github.mvp.view.fragment.RepositoriesFragment;
import com.ivaylok.github.service.GithubService;
import com.ivaylok.github.service.RepoViewInterface;
import com.ivaylok.github.service.SingleRepoInterface;
import com.ivaylok.github.utils.GithubClickListener;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class RepoActivity extends AppCompatActivity implements SingleRepoInterface, GithubClickListener {

    @Inject
    GithubService mService;

    public static final String EXTRA_MESSAGE = "com.ivaylok.github.MESSAGE";
    private ProgressDialog mDialog;
    private SingleRepoPresenter mPresenter;
    private SingleRepoAdapter mAdapter;

    RecyclerView mRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_layout);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        Intent repoIntent = getIntent();
        String mRepoName = repoIntent.getStringExtra(RepositoriesFragment.EXTRA_MESSAGE);

        resolveDependency();
        configView();
        mPresenter = new SingleRepoPresenter(RepoActivity.this);
        mPresenter.onCreate();
    }

    private void configView() {

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new SingleRepoAdapter(this, getLayoutInflater());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void resolveDependency() {
        ((GithubApplication) getApplication())
                .getmApiComponent()
                .inject(RepoActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
        mPresenter.fetchSingleRepos();
        mDialog = new ProgressDialog(RepoActivity.this);
        mDialog.setIndeterminate(true);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setTitle("Downloading List");
        mDialog.setMessage("Please wait...");
        mDialog.show();
    }

    @Override
    public void onClick(int position, String name) {
        Toast.makeText(getApplicationContext(), "You just clicked on " + name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCompleted() {
        mDialog.dismiss();
    }

    @Override
    public void onError(String message) {
        mDialog.dismiss();
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSingleRepo(List<SingleRepoResponse> singleRepoResponse) {
        mAdapter.addSingleRepos(singleRepoResponse);
    }

    @Override
    public Observable<List<SingleRepoResponse>> getSingleRepo() {
        return mService.getSingleRepo();
    }
}
