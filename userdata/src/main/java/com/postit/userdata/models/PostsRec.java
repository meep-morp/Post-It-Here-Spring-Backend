package com.postit.userdata.models;

public class PostsRec {

    private long n_results;

    private String title;

    private String selftext;

    public PostsRec(long n_results, String title, String selftext) {
        this.n_results = n_results;
        this.title = title;
        this.selftext = selftext;
    }

    public long getN_results() {
        return n_results;
    }

    public void setN_results(long n_results) {
        this.n_results = n_results;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSelftext() {
        return selftext;
    }

    public void setSelftext(String selftext) {
        this.selftext = selftext;
    }
}
