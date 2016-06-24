package com.ivaylok.github.service;


import com.ivaylok.github.mvp.model.FollowersResponse;
import com.ivaylok.github.mvp.model.FollowingsResponse;
import com.ivaylok.github.mvp.model.RepoResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import rx.Observable;

public interface GithubService {


//    Registered user -----------

    @Headers({"Accept: application/vnd.github.v3+json", "Authorization: token legit"})
    @GET("/user/repos")
    Observable<List<RepoResponse>> getRepos();

    @Headers({"Accept: application/vnd.github.v3+json", "Authorization: token legit"})
    @GET("/user/followers")
    Observable<List<FollowersResponse>> getFollowers();



//    Public -----------------

    @GET("/users/JakeWharton/repos")
    Observable<List<RepoResponse>> getPublicRepos();

    @GET("/users/JakeWharton/followers")
    Observable<List<FollowersResponse>> getPublicFollowers();

    @GET("/users/JakeWharton/following")
    Observable<List<FollowingsResponse>> getPublicFollowings();

}
