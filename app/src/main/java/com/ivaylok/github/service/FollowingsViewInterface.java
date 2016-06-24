package com.ivaylok.github.service;

import com.ivaylok.github.mvp.model.FollowingsResponse;

import java.util.List;

import rx.Observable;

public interface FollowingsViewInterface {

    void onCompleted();

    void onError(String message);

    void onFollowings(List<FollowingsResponse> followingsResponses);

    Observable<List<FollowingsResponse>> getFollowings();
}
