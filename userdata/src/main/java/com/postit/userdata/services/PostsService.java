package com.postit.userdata.services;

import com.postit.userdata.models.Posts;
import com.postit.userdata.models.Recommendations;
import org.springframework.transaction.annotation.Transactional;

public interface PostsService {

    @Transactional
    Posts save(Posts post);

    Recommendations findSubreddits(long num, Posts posts);
}
