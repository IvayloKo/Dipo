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
import com.ivaylok.github.mvp.model.NewsResponse;
import com.ivaylok.github.mvp.presenter.NewsPresenter;
import com.ivaylok.github.mvp.view.adapter.NewsAdapter;
import com.ivaylok.github.service.GithubService;
import com.ivaylok.github.service.NewsViewInterface;
import com.ivaylok.github.utils.GithubClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import rx.Observable;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment implements NewsViewInterface, GithubClickListener {

    @Inject
    GithubService mService;

    public static final String EXTRA_MESSAGE = "com.ivaylok.github.MESSAGE";
    private ProgressDialog mDialog;
    private NewsPresenter mPresenter;
    private NewsAdapter mAdapter;

    RecyclerView mRecyclerView;


    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        resolveDependency();
        ButterKnife.bind(getActivity());

        mPresenter = new NewsPresenter(NewsFragment.this);
        mPresenter.onCreate();
    }

    private void resolveDependency() {
        ((GithubApplication) getActivity().getApplication())
                .getmApiComponent()
                .inject(NewsFragment.this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recycler_layout, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);

        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new NewsAdapter(NewsFragment.this, inflater);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
        mPresenter.fetchNews();
        mDialog = new ProgressDialog(getActivity());
        mDialog.setIndeterminate(true);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setTitle("Downloading List");
        mDialog.setMessage("Please wait...");
        mDialog.show();
    }

    @Override
    public void onClick(int position, String name) {
        Toast.makeText(getActivity(), "You just clicked on " + name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCompleted() {
        mDialog.dismiss();
    }

    @Override
    public void onError(String message) {
        mDialog.dismiss();
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNews(List<NewsResponse> newsResponses) {
        mAdapter.addNews(newsResponses);
    }

    @Override
    public Observable<List<NewsResponse>> getNews() {
        return mService.getPublicNews();
    }
}
