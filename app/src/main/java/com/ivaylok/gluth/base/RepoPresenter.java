package com.ivaylok.gluth.base;

import com.ivaylok.gluth.model.RepoResponse;
import com.ivaylok.gluth.service.RepoViewInterface;

import java.util.List;

import rx.Observer;

/**
 * Created by smn on 6/17/16.
 */
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
