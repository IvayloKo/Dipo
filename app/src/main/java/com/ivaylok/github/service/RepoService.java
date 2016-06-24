package com.ivaylok.github.service;


import com.ivaylok.github.mvp.model.RepoResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import rx.Observable;
import rx.Observer;

public interface RepoService {


//    Registered user
    @Headers({"Accept: application/vnd.github.v3+json", "Authorization: token ecf8e9f4860f529af93bb6ad58f4cd3e0d693428"})
    @GET("/user/repos")
    Observable<List<RepoResponse>> getRepos();

//    Public
    @GET("/users/{user}/repos")
    Observable<List<RepoResponse>> getPublicRepos();

}
