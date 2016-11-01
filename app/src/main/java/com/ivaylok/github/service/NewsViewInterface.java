package com.ivaylok.github.service;

import com.ivaylok.github.mvp.model.NewsResponse;
import com.ivaylok.github.mvp.model.UserResponse;

import java.util.List;

import rx.Observable;

public interface NewsViewInterface {

    void onCompleted();

    void onError(String message);

    void onNews(List<NewsResponse> newsResponses);

    void onUser(UserResponse userResponse);

    Observable<UserResponse> getUser();

    Observable<List<NewsResponse>> getNews();
}
