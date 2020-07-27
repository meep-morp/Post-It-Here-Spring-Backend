package com.postit.userdata.models;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roleid;

    private String name;

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
