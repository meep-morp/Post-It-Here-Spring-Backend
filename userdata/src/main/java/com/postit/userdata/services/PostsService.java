package com.postit.userdata.services;

import com.postit.userdata.models.Posts;
import com.postit.userdata.models.Recommendations;

public interface PostsService {

    Posts save(Posts post);

    Recommendations findSubreddits(long num, Posts posts);
}
