package com.ivaylok.gluth.dependencies;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by smn on 6/17/16.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomScope {
}
