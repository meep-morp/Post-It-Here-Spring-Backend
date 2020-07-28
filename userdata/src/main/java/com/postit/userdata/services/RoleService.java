package com.postit.userdata.services;

import com.postit.userdata.models.Role;
import org.springframework.transaction.annotation.Transactional;

public interface RoleService {
    @Transactional
    Role save(Role role);

    Role findRoleById(long id);

    Role findByName(String name);
}
