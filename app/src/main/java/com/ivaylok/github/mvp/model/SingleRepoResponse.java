package com.ivaylok.github.mvp.model;

import com.google.gson.annotations.Expose;

public class SingleRepoResponse {

    @Expose
    private String name;

    @Expose
    private String download_url;

    @Expose
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
