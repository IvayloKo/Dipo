package com.ivaylok.github.mvp.presenter;

import com.ivaylok.github.mvp.model.FollowersResponse;
import com.ivaylok.github.service.FollowersViewInterface;

import java.util.List;

import rx.Observer;

public class FollowersPresenter extends BasePresenter implements Observer<List<FollowersResponse>> {

    private FollowersViewInterface mViewInterface;

    public FollowersPresenter(FollowersViewInterface mViewInterface) {
        this.mViewInterface = mViewInterface;
    }

    @Override
    public void onCompleted() {
        mViewInterface.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        mViewInterface.onError(e.getMessage());
    }

    @Override
    public void onNext(List<FollowersResponse> followersResponses) {
        mViewInterface.onFollowers(followersResponses);
    }

//    TODO refactor this method
    public void fetchResponse() {
        unSubscribeAll();
        subscribe(mViewInterface.getFollowers(), FollowersPresenter.this);
    }
}
