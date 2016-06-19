package com.ivaylok.gluth.dependencies;

import com.ivaylok.gluth.service.RepoService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by smn on 6/17/16.
 */
@Module
public class ApiModule {

    @Provides
    @CustomScope
    RepoService provideRepoService(Retrofit retrofit) {
        return retrofit.create(RepoService.class);
    }
}
