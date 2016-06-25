package com.ivaylok.github.mvp.presenter;

import com.ivaylok.github.mvp.model.StarsResponse;
import com.ivaylok.github.service.StarsViewInterface;

import java.util.List;

import rx.Observer;

public class StarsPresenter extends BasePresenter implements Observer<List<StarsResponse>> {

    private StarsViewInterface mViewInterface;

    public StarsPresenter(StarsViewInterface mViewInterface) {
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
    public void onNext(List<StarsResponse> starsResponses) {
        mViewInterface.onStars(starsResponses);
    }

    public void fetchStars() {
        unSubscribeAll();
        subscribe(mViewInterface.getStars(), StarsPresenter.this);
    }
}
