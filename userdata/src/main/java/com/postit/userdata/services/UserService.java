package com.postit.userdata.services;

import com.postit.userdata.models.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    @Transactional
    User save(User user);

    List<User> findAll();

    User findByUsername(String name);

    User findById(long id);

    @Transactional
    void delete(long id);

    User getCurrentUserInfo();
}
