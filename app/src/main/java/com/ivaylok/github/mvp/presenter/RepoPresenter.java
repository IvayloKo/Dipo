package com.ivaylok.github.mvp.presenter;

import com.ivaylok.github.mvp.model.RepoResponse;
import com.ivaylok.github.service.RepoViewInterface;

import java.util.List;

import rx.Observer;

public class RepoPresenter extends BasePresenter implements Observer<List<RepoResponse>> {

    private RepoViewInterface mViewInterface;

    public RepoPresenter(RepoViewInterface viewInterface) {
        mViewInterface = viewInterface;
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
    public void onNext(List<RepoResponse> repoResponses) {
        mViewInterface.onRepos(repoResponses);
    }

    public void fetchRepose() {
        unSubscribeAll();
        subscribe(mViewInterface.getRepos(), RepoPresenter.this);
    }
}
