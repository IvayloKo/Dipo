package com.ivaylok.github.mvp.view.fragment;


import android.app.ProgressDialog;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ivaylok.github.R;
import com.ivaylok.github.application.GithubApplication;
import com.ivaylok.github.mvp.model.NewsResponse;
import com.ivaylok.github.mvp.model.UserResponse;
import com.ivaylok.github.mvp.presenter.NewsPresenter;
import com.ivaylok.github.mvp.presenter.UserPresenter;
import com.ivaylok.github.mvp.view.activity.OverviewActivity;
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
    private NewsPresenter mNewsPresenter;
    private UserPresenter mUserPresenter;
    private NewsAdapter mAdapter;

    RecyclerView mRecyclerView;

    private TextView mUserName, mUserLogin, mUserLocation, mUserCreatedAt, mUserBio;
    private ImageView mUserAvatar;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        resolveDependency();
        ButterKnife.bind(getActivity());

        mNewsPresenter = new NewsPresenter(NewsFragment.this);
        mNewsPresenter.onCreate();
        mUserPresenter = new UserPresenter(NewsFragment.this);
        mUserPresenter.onCreate();
    }

    private void resolveDependency() {
        ((GithubApplication) getActivity().getApplication())
                .getmApiComponent()
                .inject(NewsFragment.this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.overview_recycler, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);

        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new NewsAdapter(NewsFragment.this, inflater);
        mRecyclerView.setAdapter(mAdapter);

        mUserAvatar = (ImageView) rootView.findViewById(R.id.ivOverviewAvatar);
        mUserName = (TextView) rootView.findViewById(R.id.tvOverviewName);
        mUserLogin = (TextView) rootView.findViewById(R.id.tvOverviewLogin);
        mUserLocation = (TextView) rootView.findViewById(R.id.tvOverviewLocation);
        mUserCreatedAt = (TextView) rootView.findViewById(R.id.tvOverviewCreatedAt);
        mUserBio = (TextView) rootView.findViewById(R.id.tvOverviewBio);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mNewsPresenter.onResume();
        mNewsPresenter.fetchNews();
        mUserPresenter.onResume();
        mUserPresenter.fetchUser();
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
    public void onCompleted() { mDialog.dismiss(); }

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
        return mService.getPublicNews(OverviewActivity.mCurrentUser);
    }

    @Override
    public void onUser(UserResponse userResponse) {
        mUserName.setText(userResponse.getName());
        mUserLogin.setText(userResponse.getLogin());
        mUserLocation.setText(userResponse.getLocation());
        mUserCreatedAt.setText(userResponse.getCreated_at());
        mUserBio.setText(userResponse.getBio());
    }

    @Override
    public Observable<UserResponse> getUser() {
        return mService.getPublicUser(OverviewActivity.mCurrentUser);
    }


}
