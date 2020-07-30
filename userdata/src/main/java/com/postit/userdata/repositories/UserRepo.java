package com.postit.userdata.repositories;

import com.postit.userdata.models.SavedPosts;
import com.postit.userdata.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {

    User findByUsername(String name);

}
