package com.postit.userdata.services;

import com.postit.userdata.models.Role;
import com.postit.userdata.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public Role save(Role role) {
        return roleRepo.save(role);
    }

}
