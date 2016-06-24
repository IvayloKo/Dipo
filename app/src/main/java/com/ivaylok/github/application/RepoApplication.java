package com.ivaylok.github.application;

import android.app.Application;

import com.ivaylok.github.dependencies.ApiComponent;
import com.ivaylok.github.dependencies.DaggerApiComponent;
import com.ivaylok.github.dependencies.DaggerNetworkComponent;
import com.ivaylok.github.dependencies.NetworkComponent;
import com.ivaylok.github.dependencies.NetworkModule;
import com.ivaylok.github.mvp.model.Constant;

public class RepoApplication extends Application {

    private ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        resolveDependency();
        super.onCreate();
    }

    private void resolveDependency() {
        mApiComponent = DaggerApiComponent.builder()
                .networkComponent(getNetworkComponent())
                .build();
    }

    private NetworkComponent getNetworkComponent() {
        return DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(Constant.BASE_URL))
                .build();
    }

    public ApiComponent getmApiComponent() {
        return mApiComponent;
    }
}
