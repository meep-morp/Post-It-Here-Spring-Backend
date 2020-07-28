package com.postit.userdata.services;

import com.postit.userdata.exceptions.ResourceNotFoundException;
import com.postit.userdata.models.Role;
import com.postit.userdata.models.User;
import com.postit.userdata.models.UserRoles;
import com.postit.userdata.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private HelperFunctions helperFunctions;

    @Autowired
    private RoleService roleService;

    @Override
    @Transactional
    public User save(User user) {
        User newUser = new User();
        if (user.getUserid() != 0) {
            userRepo.findById(user.getUserid())
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("User %s not found.", user.getUserid())));
            newUser.setUserid(user.getUserid());
        }

        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());

        newUser.getUserroles().clear();
        for(UserRoles u : user.getUserroles()) {
            Role newRole = roleService.findRoleById(u.getRole().getRoleid());

            newUser.getUserroles().add(new UserRoles(newUser, newRole));
        }



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

    @Override
    public User findById(long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User %s not found", id)));
    }

    @Override
    @Transactional
    public void delete(long id) {
        userRepo.deleteById(id);
    }

    @Override
    public User getCurrentUserInfo() {
        return userRepo.findByUsername(helperFunctions.getCurrentAuditor());
    }
}
