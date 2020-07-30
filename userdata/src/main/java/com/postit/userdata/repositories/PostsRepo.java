package com.postit.userdata.repositories;

import com.postit.userdata.models.Posts;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostsRepo extends CrudRepository<Posts, Long> {
    List<Posts> findByTitleContainingIgnoreCase(String title);
}
