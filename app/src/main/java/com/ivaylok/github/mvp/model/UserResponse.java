package com.ivaylok.github.mvp.model;

import com.google.gson.annotations.Expose;

/**
 * Created by smn on 11/1/16.
 */

public class UserResponse {

    private String id;
    @Expose
    private String avatar_url;
    @Expose
    private String name;
    @Expose
    private String login;
    @Expose
    private String email;
    @Expose
    private String location;
    @Expose
    private String created_at;
    @Expose
    private String bio;

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getLocation() {
        return location;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getBio() {
        return bio;
    }
}
