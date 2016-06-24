package com.ivaylok.github.mvp.presenter;

import com.ivaylok.github.mvp.model.FollowingsResponse;
import com.ivaylok.github.service.FollowingsViewInterface;

import java.util.List;

import rx.Observer;

public class FollowingsPresenter extends BasePresenter implements Observer<List<FollowingsResponse>> {

    private FollowingsViewInterface mViewInterface;

    public FollowingsPresenter(FollowingsViewInterface mViewInterface) {
        this.mViewInterface = mViewInterface;
    }

    @Override
    public void onCompleted() { mViewInterface.onCompleted(); }

    @Override
    public void onError(Throwable e) { mViewInterface.onError(e.getMessage()); }

    @Override
    public void onNext(List<FollowingsResponse> followingsResponses) {
        mViewInterface.onFollowings(followingsResponses);
    }

    public void fetchResponse() {
        unSubscribeAll();
        subscribe(mViewInterface.getFollowings(), FollowingsPresenter.this);
    }
}
