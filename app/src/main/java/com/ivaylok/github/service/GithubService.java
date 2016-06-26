package com.ivaylok.github.service;


import com.ivaylok.github.mvp.model.FollowersResponse;
import com.ivaylok.github.mvp.model.FollowingsResponse;
import com.ivaylok.github.mvp.model.NewsResponse;
import com.ivaylok.github.mvp.model.RepoResponse;
import com.ivaylok.github.mvp.model.SingleRepoResponse;
import com.ivaylok.github.mvp.model.StarsResponse;

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

    @Headers({"Accept: application/vnd.github.v3+json", "Authorization: token legit"})
    @GET("/users/JakeWharton/received_events")
    Observable<List<NewsResponse>> getPublicNews();

    @Headers({"Accept: application/vnd.github.v3+json", "Authorization: token legit"})
    @GET("/users/JakeWharton/repos")
    Observable<List<RepoResponse>> getPublicRepos();

    @Headers({"Accept: application/vnd.github.v3+json", "Authorization: token legit"})
    @GET("/users/JakeWharton/starred")
    Observable<List<StarsResponse>> getPublicStars();

    @Headers({"Accept: application/vnd.github.v3+json", "Authorization: token legit"})
    @GET("/users/JakeWharton/followers")
    Observable<List<FollowersResponse>> getPublicFollowers();

    @Headers({"Accept: application/vnd.github.v3+json", "Authorization: token legit"})
    @GET("/users/JakeWharton/following")
    Observable<List<FollowingsResponse>> getPublicFollowings();

    @Headers({"Accept: application/vnd.github.v3+json", "Authorization: token legit"})
    @GET("/repos/JakeWharton/butterknife/contents")
    Observable<List<SingleRepoResponse>> getSingleRepo();

}
