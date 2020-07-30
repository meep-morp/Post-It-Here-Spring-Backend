package com.postit.userdata.services;

import com.postit.userdata.models.Posts;
import com.postit.userdata.models.RedditApi;
import com.postit.userdata.models.SavedPosts;
import com.postit.userdata.models.Subreddit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SubredditService {

    @Transactional
    Subreddit save(Subreddit subreddit);

    List<Subreddit> findAll();

    @Transactional
    RedditApi getPosts(String subname);

    Subreddit findBySubName(String subname);

    Subreddit findById(long id);

    @Transactional
    SavedPosts savePost(Posts posts, String subname);

    @Transactional
    void delete(long id);
}
