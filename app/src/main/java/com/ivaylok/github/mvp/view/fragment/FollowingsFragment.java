package com.ivaylok.github.mvp.view.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ivaylok.github.R;
import com.ivaylok.github.application.GithubApplication;
import com.ivaylok.github.mvp.model.FollowingsResponse;
import com.ivaylok.github.mvp.presenter.FollowingsPresenter;
import com.ivaylok.github.mvp.view.activity.OverviewActivity;
import com.ivaylok.github.mvp.view.adapter.FollowingsAdapter;
import com.ivaylok.github.service.FollowingsViewInterface;
import com.ivaylok.github.service.GithubService;
import com.ivaylok.github.utils.GithubClickListener;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * A simple {@link Fragment} subclass.
 */
public class FollowingsFragment extends Fragment implements FollowingsViewInterface, GithubClickListener {

    @Inject
    GithubService mService;

    private ProgressDialog mDialog;
    private FollowingsPresenter mPresenter;
    private FollowingsAdapter mAdapter;

    RecyclerView mRecyclerView;


    public FollowingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        resolveDependency();
        mPresenter = new FollowingsPresenter(FollowingsFragment.this);
    }

    private void resolveDependency() {
        ((GithubApplication) getActivity().getApplication())
                .getmApiComponent()
                .inject(FollowingsFragment.this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootVIew = inflater.inflate(R.layout.recycler_layout, container, false);
        mRecyclerView = (RecyclerView) rootVIew.findViewById(R.id.recyclerview);

        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new FollowingsAdapter(FollowingsFragment.this, inflater);
        mRecyclerView.setAdapter(mAdapter);

        return rootVIew;
    }

    @Override
    public void onClick(int position, String name) {
        Toast.makeText(getActivity(), "You are now browsing " + name + "'s profile", Toast.LENGTH_SHORT).show();
        OverviewActivity.mCurrentUser = name;
        onResume();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
        mPresenter.fetchResponse();
        mDialog = new ProgressDialog(getActivity());
        mDialog.setIndeterminate(true);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setTitle("Downloading List");
        mDialog.setMessage("Please wait...");
        mDialog.show();
    }

    @Override
    public void onCompleted() { mDialog.dismiss(); }

    @Override
    public void onError(String message) {
        mDialog.dismiss();
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFollowings(List<FollowingsResponse> followingsResponses) {
        mAdapter.addFollowings(followingsResponses);
    }

    @Override
    public Observable<List<FollowingsResponse>> getFollowings() {
        return mService.getPublicFollowings(OverviewActivity.mCurrentUser);
    }


}
