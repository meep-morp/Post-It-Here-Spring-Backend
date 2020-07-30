package com.postit.userdata.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedditParentData {

    private List<RedditApiPostsParent> children;

    public RedditParentData() {
    }

    public RedditParentData(List<RedditApiPostsParent> children) {

        this.children = children;
    }

    public List<RedditApiPostsParent> getChildren() {
        return children;
    }

    public void setChildren(List<RedditApiPostsParent> children) {
        this.children = children;
    }
}
