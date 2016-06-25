package com.ivaylok.github.mvp.model;

import com.google.gson.annotations.Expose;

public class StarsResponse {

    @Expose
    private int id;

    @Expose
    private String name;

    @Expose
    private String full_name;

    @Expose
    private String stargazers_count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(String stargazers_count) {
        this.stargazers_count = stargazers_count;
    }
}
