package com.postit.userdata.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RedditApi {

    private RedditParentData data;

    public RedditApi() {
    }

    public RedditApi(RedditParentData data) {
        this.data = data;
    }

    public RedditParentData getData() {
        return data;
    }

    public void setData(RedditParentData data) {
        this.data = data;
    }
}
