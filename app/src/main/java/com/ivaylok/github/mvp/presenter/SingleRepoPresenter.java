package com.ivaylok.github.mvp.presenter;

import com.ivaylok.github.mvp.model.SingleRepoResponse;
import com.ivaylok.github.service.SingleRepoInterface;

import java.util.List;

import rx.Observer;

public class SingleRepoPresenter extends BasePresenter implements Observer<List<SingleRepoResponse>> {

    private SingleRepoInterface mViewInterface;

    public SingleRepoPresenter(SingleRepoInterface mViewInterface) {
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
    public void onNext(List<SingleRepoResponse> singleRepoResponseList) {
        mViewInterface.onSingleRepo(singleRepoResponseList);
    }

    public void fetchSingleRepos() {
        unSubscribeAll();
        subscribe(mViewInterface.getSingleRepo(), SingleRepoPresenter.this);
    }
}
