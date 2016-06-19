package com.ivaylok.gluth.ui;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ivaylok.gluth.R;
import com.ivaylok.gluth.application.RepoApplication;
import com.ivaylok.gluth.base.RepoPresenter;
import com.ivaylok.gluth.model.RepoAdapter;
import com.ivaylok.gluth.model.RepoResponse;
import com.ivaylok.gluth.service.RepoService;
import com.ivaylok.gluth.service.RepoViewInterface;

import butterknife.Bind;
import butterknife.ButterKnife;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class MainActivity extends AppCompatActivity implements RepoViewInterface, RepoAdapter.RepoClickListener {

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

        resolveDependency();

        ButterKnife.bind(MainActivity.this);

        configVIews();
        mPresenter = new RepoPresenter(MainActivity.this);
        mPresenter.onCreate();

    }

    private void resolveDependency() {
        ((RepoApplication) getApplication())
                .getmApiComponent()
                .inject(MainActivity.this);
    }

    private void configVIews() {
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
        mDialog = new ProgressDialog(MainActivity.this);
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
    }
}
