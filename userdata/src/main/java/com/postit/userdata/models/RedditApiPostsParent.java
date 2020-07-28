package com.postit.userdata.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RedditApiPostsParent {
    private String kind;
    private RedditApiPostData data;

    public RedditApiPostsParent() {
    }

    public RedditApiPostsParent(String kind, RedditApiPostData data) {
        this.kind = kind;
        this.data = data;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public RedditApiPostData getData() {
        return data;
    }

    public void setData(RedditApiPostData data) {
        this.data = data;
    }
}
