package com.postit.userdata.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subreddits")
public class Subreddit extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long subid;

    private String title;

    @Column(length = 900)
    private String description;

    public Subreddit(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @OneToMany(mappedBy = "subreddit", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "subreddit")
    private Set<UserSubs> usersubs = new HashSet<>();

    @OneToMany(mappedBy = "subreddit", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "subreddit")
    private Set<SavedPosts> savedposts = new HashSet<>();

    public Subreddit() {
    }

    public Set<UserSubs> getUsersubs() {
        return usersubs;
    }

    public void setUsersubs(Set<UserSubs> userSubs) {
        this.usersubs = userSubs;
    }

    public Set<SavedPosts> getSavedposts() {
        return savedposts;
    }

    public void setSavedposts(Set<SavedPosts> savedPosts) {
        this.savedposts = savedPosts;
    }

    public long getSubid() {
        return subid;
    }

    public void setSubid(long subid) {
        this.subid = subid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
