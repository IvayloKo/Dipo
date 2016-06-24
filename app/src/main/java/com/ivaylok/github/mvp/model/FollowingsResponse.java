package com.ivaylok.github.mvp.model;

import com.google.gson.annotations.Expose;

public class FollowingsResponse {

    @Expose
    private String login;

    @Expose
    private String avatar_url;

    @Expose
    private int id;

    public String getLogin() { return login; }

    public void setLogin(String login) { this.login = login; }

    public String getAvatar_url() { return avatar_url; }

    public void setAvatar_url(String avatar_url) { this.avatar_url = avatar_url; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
}
