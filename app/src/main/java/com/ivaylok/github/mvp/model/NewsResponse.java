package com.ivaylok.github.mvp.model;

import com.google.gson.annotations.Expose;

public class NewsResponse {

    @Expose
    private String id;

    @Expose
    private String type;

    @Expose
    private Actor actor;

    private Repo repo;

    private Payload payload;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getActor() {
        return actor.login;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public String getRepo() {
        return repo.getName();
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

    public String getPayload() {
        return payload.action;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public class Actor {

        @Expose
        private String login;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }
    }

    public class Repo {

        @Expose
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class Payload {

        @Expose
        private String action;

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }
    }


}
