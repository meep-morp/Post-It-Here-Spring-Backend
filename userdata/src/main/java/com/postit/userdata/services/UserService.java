package com.postit.userdata.services;

import com.postit.userdata.models.User;

import java.util.List;

public interface UserService {
    User save(User user);

    List<User> findAll();

    User findByUsername(String name);

    User getCurrentUserInfo();
}
