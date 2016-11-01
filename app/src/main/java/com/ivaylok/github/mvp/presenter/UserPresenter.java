package com.ivaylok.github.mvp.presenter;

import com.ivaylok.github.mvp.model.UserResponse;
import com.ivaylok.github.service.NewsViewInterface;

import rx.Observer;

/**
 * Created by smn on 11/1/16.
 */

public class UserPresenter extends BasePresenter implements Observer<UserResponse> {

    private NewsViewInterface mViewInterface;

    public UserPresenter(NewsViewInterface mViewInterface) {this.mViewInterface = mViewInterface;}

    @Override
    public void onCompleted() {
        mViewInterface.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        mViewInterface.onError(e.getMessage());
    }

    @Override
    public void onNext(UserResponse userResponse) {
        mViewInterface.onUser(userResponse);
    }

    public void fetchUser() {
        unSubscribeAll();
        subscribe(mViewInterface.getUser(), UserPresenter.this);
    }
}
