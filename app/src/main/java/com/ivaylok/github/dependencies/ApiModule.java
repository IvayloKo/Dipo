package com.ivaylok.github.dependencies;

import com.ivaylok.github.service.GithubService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {

    @Provides
    @CustomScope
    GithubService provideRepoService(Retrofit retrofit) {
        return retrofit.create(GithubService.class);
    }
}
