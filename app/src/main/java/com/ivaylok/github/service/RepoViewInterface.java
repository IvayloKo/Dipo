package com.ivaylok.github.service;

import com.ivaylok.github.model.RepoResponse;

import java.util.List;

import rx.Observable;


public interface RepoViewInterface {

    void onCompleted();

    void onError(String message);

    void onRepos(List<RepoResponse> repoResponses);

    Observable<List<RepoResponse>> getRepos();
}
