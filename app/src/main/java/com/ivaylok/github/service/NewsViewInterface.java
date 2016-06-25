package com.ivaylok.github.service;

import com.ivaylok.github.mvp.model.NewsResponse;

import java.util.List;

import rx.Observable;

public interface NewsViewInterface {

    void onCompleted();

    void onError(String message);

    void onNews(List<NewsResponse> newsResponses);

    Observable<List<NewsResponse>> getNews();
}
