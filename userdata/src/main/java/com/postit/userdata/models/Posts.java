package com.postit.userdata.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Posts extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postid;

    private String title;

    @Column(length = 900)
    private String selftext;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "posts", allowSetters = true)
    private Set<SavedPosts> savedposts = new HashSet<>();

    public Posts(String title, String selftext) {
        this.title = title;
        this.selftext = selftext;
    }

    public Posts() {
    }

    public Set<SavedPosts> getSavedposts() {
        return savedposts;
    }

    public void setSavedposts(Set<SavedPosts> savedPosts) {
        this.savedposts = savedPosts;
    }

    public long getPostid() {
        return postid;
    }

    public void setPostid(long postid) {
        this.postid = postid;
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

    public void setSelftext(String contents) {
        this.selftext = contents;
    }
}
