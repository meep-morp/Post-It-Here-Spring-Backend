package com.postit.userdata.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedditParentData {

    private String modhash;
    private long dist;
    private List<RedditApiPostsParent> children;

    public RedditParentData() {
    }

    public RedditParentData(String modhash, long dist, List<RedditApiPostsParent> children) {
        this.modhash = modhash;
        this.dist = dist;
        this.children = children;
    }

    public String getModhash() {
        return modhash;
    }

    public void setModhash(String modhash) {
        this.modhash = modhash;
    }

    public long getDist() {
        return dist;
    }

    public void setDist(long dist) {
        this.dist = dist;
    }

    public List<RedditApiPostsParent> getChildren() {
        return children;
    }

    public void setChildren(List<RedditApiPostsParent> children) {
        this.children = children;
    }
}
