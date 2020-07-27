package com.postit.userdata.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "userposts")
public class SavedPosts extends Auditable implements Serializable{

    @Id
    @ManyToOne
    @JoinColumn(name = "subid")
    @JsonIgnoreProperties(value = "savedposts")
    private Subreddit subreddit;

    @Id
    @ManyToOne
    @JoinColumn(name = "postid")
    @JsonIgnoreProperties(value = "savedposts")
    private Posts posts;

    public SavedPosts(Subreddit subreddit, Posts posts) {
        this.subreddit = subreddit;
        this.posts = posts;
    }

    public SavedPosts() {
    }

    public Subreddit getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(Subreddit subreddit) {
        this.subreddit = subreddit;
    }

    public Posts getPosts() {
        return posts;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }
}
