package com.postit.userdata.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RedditApi {

    private String kind;
    private RedditParentData data;

    public RedditApi() {
    }

    public RedditApi(String kind, RedditParentData data) {
        this.kind = kind;
        this.data = data;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public RedditParentData getData() {
        return data;
    }

    public void setData(RedditParentData data) {
        this.data = data;
    }
}
