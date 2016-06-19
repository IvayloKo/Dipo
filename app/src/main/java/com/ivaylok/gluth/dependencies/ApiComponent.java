package com.ivaylok.gluth.dependencies;

import com.ivaylok.gluth.ui.MainActivity;

import dagger.Component;

/**
 * Created by smn on 6/17/16.
 */
@CustomScope
@Component(modules = ApiModule.class, dependencies = NetworkComponent.class)
public interface ApiComponent {

    void inject(MainActivity activity);
}
