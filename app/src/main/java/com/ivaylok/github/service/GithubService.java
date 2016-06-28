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
import retrofit2.http.Path;
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
    @GET("/users/{user}/received_events")
    Observable<List<NewsResponse>> getPublicNews(@Path("user") String user);

    @Headers({"Accept: application/vnd.github.v3+json", "Authorization: token legit"})
    @GET("/users/{user}/repos")
    Observable<List<RepoResponse>> getPublicRepos(@Path("user") String user);

    @Headers({"Accept: application/vnd.github.v3+json", "Authorization: token legit"})
    @GET("/users/{user}/starred")
    Observable<List<StarsResponse>> getPublicStars(@Path("user") String user);

    @Headers({"Accept: application/vnd.github.v3+json", "Authorization: token legit"})
    @GET("/users/{user}/followers")
    Observable<List<FollowersResponse>> getPublicFollowers(@Path("user") String user);

    @Headers({"Accept: application/vnd.github.v3+json", "Authorization: token legit"})
    @GET("/users/{user}/following")
    Observable<List<FollowingsResponse>> getPublicFollowings(@Path("user") String user);

    @Headers({"Accept: application/vnd.github.v3+json", "Authorization: token legit"})
    @GET("/repos/{user}/butterknife/contents")
    Observable<List<SingleRepoResponse>> getSingleRepo(@Path("user") String user);

}
