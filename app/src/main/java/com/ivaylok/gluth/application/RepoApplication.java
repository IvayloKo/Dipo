package com.ivaylok.gluth.application;

import android.app.Application;

import com.ivaylok.gluth.dependencies.ApiComponent;
import com.ivaylok.gluth.dependencies.DaggerApiComponent;
import com.ivaylok.gluth.dependencies.DaggerNetworkComponent;
import com.ivaylok.gluth.dependencies.NetworkComponent;
import com.ivaylok.gluth.dependencies.NetworkModule;
import com.ivaylok.gluth.model.Constant;

/**
 * Created by smn on 6/17/16.
 */
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
