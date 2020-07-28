package com.postit.userdata.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roleid;

    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "role", allowSetters = true)
    Set<UserRoles> userRoles = new HashSet<>();

    public Role(String name) {
        this.name = name;
    }

    public Role() {
    }

    public long getRoleid() {
        return roleid;
    }

    public void setRoleid(long roleid) {
        this.roleid = roleid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
