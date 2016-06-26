package com.ivaylok.github.service;

import com.ivaylok.github.mvp.model.SingleRepoResponse;

import java.util.List;

import rx.Observable;

public interface SingleRepoInterface {

    void onCompleted();

    void onError(String message);

    void onSingleRepo(List<SingleRepoResponse> singleRepoResponse);

    Observable<List<SingleRepoResponse>> getSingleRepo();
}
