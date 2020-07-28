package com.postit.userdata.repositories;

import com.postit.userdata.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role, Long> {

    Role findByNameIgnoringCase(String name);

}
