package com.postit.userdata.repositories;

import com.postit.userdata.models.Posts;
import org.springframework.data.repository.CrudRepository;

public interface PostsRepo extends CrudRepository<Posts, Long> {
}
