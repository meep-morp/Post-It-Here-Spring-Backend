package com.postit.userdata.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    private String username;

    private String password;

    public User(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "user", allowSetters = true)
    private Set<UserSubs> usersubs = new HashSet<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<UserSubs> getUsersubs() {
        return usersubs;
    }

    public void setUsersubs(Set<UserSubs> userSubs) {
        this.usersubs = userSubs;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
