package com.postit.userdata.services;

import com.postit.userdata.models.User;
import com.postit.userdata.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Override
    public User save(User user) {
       return userRepo.save(user);
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        userRepo.findAll().iterator().forEachRemaining(userList::add);

        return userList;
    }

    @Override
    public User findByUsername(String name) {
        return userRepo.findByUsername(name);
    }
}
