package com.postit.userdata.services;

import com.postit.userdata.exceptions.ResourceNotFoundException;
import com.postit.userdata.models.Role;
import com.postit.userdata.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo roleRepo;

    @Override
    @Transactional
    public Role save(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public Role findRoleById(long id) {
        return roleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Role %s not found.", id)));
    }

    @Override
    public Role findByName(String name) {
        return roleRepo.findByNameIgnoringCase(name);
    }

}
