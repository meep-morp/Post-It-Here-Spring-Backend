package com.postit.userdata.services;

import com.postit.userdata.models.Role;

public interface RoleService {
    Role save(Role role);

    Role findRoleById(long id);
}
