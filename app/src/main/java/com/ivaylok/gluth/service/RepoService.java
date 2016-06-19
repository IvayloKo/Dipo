package com.ivaylok.gluth.service;


import com.ivaylok.gluth.model.RepoResponse;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface RepoService {

    @GET("/users/saimancroy/repos")
    Observable<List<RepoResponse>> getRepos();

}
