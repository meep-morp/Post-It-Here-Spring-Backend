package com.postit.userdata.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RedditApiPostsParent {
    private RedditApiPostData data;

    public RedditApiPostsParent() {
    }

    public RedditApiPostsParent(RedditApiPostData data) {
        this.data = data;
    }

    public RedditApiPostData getData() {
        return data;
    }

    public void setData(RedditApiPostData data) {
        this.data = data;
    }
}
