package com.ivaylok.github.service;

import com.ivaylok.github.mvp.model.StarsResponse;

import java.util.List;

import rx.Observable;

public interface StarsViewInterface {

    void onCompleted();

    void onError(String message);

    void onStars(List<StarsResponse> starsResponses);

    Observable<List<StarsResponse>> getStars();
}
