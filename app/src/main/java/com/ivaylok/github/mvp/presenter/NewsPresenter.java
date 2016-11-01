package com.ivaylok.github.mvp.presenter;

import com.ivaylok.github.mvp.model.NewsResponse;
import com.ivaylok.github.mvp.model.UserResponse;
import com.ivaylok.github.service.NewsViewInterface;

import java.util.List;

import rx.Observer;

public class NewsPresenter extends BasePresenter implements Observer<List<NewsResponse>> {

    private NewsViewInterface mViewInterface;

    public NewsPresenter(NewsViewInterface mViewInterface) {
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
    public void onNext(List<NewsResponse> newsResponses) {

        mViewInterface.onNews(newsResponses);
    }

    public void fetchNews() {
        unSubscribeAll();
        subscribe(mViewInterface.getNews(), NewsPresenter.this);
    }
}
