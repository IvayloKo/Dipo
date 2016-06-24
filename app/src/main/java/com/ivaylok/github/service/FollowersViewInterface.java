package com.ivaylok.github.service;

import com.ivaylok.github.mvp.model.FollowersResponse;

import java.util.List;

import rx.Observable;

public interface FollowersViewInterface {

    void onCompleted();

    void onError(String message);

    void onFollowers(List<FollowersResponse> followersResponses);

    Observable<List<FollowersResponse>> getFollowers();
}
