package com.ivaylok.github.service;


import com.ivaylok.github.mvp.model.RepoResponse;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface RepoService {

    @GET("/users/JakeWharton/repos")
    Observable<List<RepoResponse>> getRepos();

}
