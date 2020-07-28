package com.postit.userdata.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usersubs")
public class UserSubs extends Auditable implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = "usersubs", allowSetters = true)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "subid")
    @JsonIgnoreProperties(value = "usersubs", allowSetters = true)
    private Subreddit subreddit;

    public UserSubs(User user, Subreddit subreddit) {
        this.user = user;
        this.subreddit = subreddit;
    }

    public UserSubs() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subreddit getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(Subreddit subreddit) {
        this.subreddit = subreddit;
    }
}
