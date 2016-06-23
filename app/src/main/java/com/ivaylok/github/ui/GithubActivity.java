package com.ivaylok.github.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ivaylok.github.R;
import com.ivaylok.github.application.RepoApplication;
import com.ivaylok.github.base.RepoPresenter;
import com.ivaylok.github.model.RepoAdapter;
import com.ivaylok.github.model.RepoResponse;
import com.ivaylok.github.service.RepoService;
import com.ivaylok.github.service.RepoViewInterface;

import butterknife.Bind;
import butterknife.ButterKnife;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class GithubActivity extends AppCompatActivity implements RepoViewInterface, RepoAdapter.RepoClickListener {

    public static final String EXTRA_MESSAGE = "com.ivaylok.github.MESSAGE";

    @Inject
    RepoService mService;

    private RepoPresenter mPresenter;

    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private RepoAdapter mAdapter;

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        resolveDependency();

        ButterKnife.bind(GithubActivity.this);

        configViews();
        mPresenter = new RepoPresenter(GithubActivity.this);
        mPresenter.onCreate();

    }

//    private void resolveDependency() {
//        ((RepoApplication) getApplication())
//                .getmApiComponent()
//                .inject(GithubActivity.this);
//    }

    private void configViews() {
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new RepoAdapter(this, getLayoutInflater());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
            super.onResume();
        mPresenter.onResume();
        mPresenter.fetchRepose();
        mDialog = new ProgressDialog(GithubActivity.this);
        mDialog.setIndeterminate(true);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setTitle("Downloading List");
        mDialog.setMessage("Please wait...");
        mDialog.show();
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
    public void onRepos(List<RepoResponse> repoResponses) {
        mAdapter.addRepos(repoResponses);
    }

    @Override
    public Observable<List<RepoResponse>> getRepos() {
        return mService.getRepos();
    }

    @Override
    public void onClick(int position, String name) {
        Toast.makeText(getApplicationContext(), "You just clicked on " + name, Toast.LENGTH_SHORT).show();
        Intent repoIntent = new Intent(this, RepoActivity.class);
        repoIntent.putExtra(EXTRA_MESSAGE, name);
        startActivity(repoIntent);
    }
}
