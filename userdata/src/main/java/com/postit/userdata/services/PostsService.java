package com.postit.userdata.services;

import com.postit.userdata.models.Posts;
import com.postit.userdata.models.Recommendations;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostsService {

    @Transactional
    Posts save(Posts post);

    Recommendations findSubreddits(long num, Posts posts);

    List<Posts> search(String title);

    List<Posts> findAll();
}
