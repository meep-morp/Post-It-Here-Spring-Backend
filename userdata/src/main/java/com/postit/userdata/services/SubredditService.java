package com.postit.userdata.services;

import com.postit.userdata.models.Subreddit;
import org.springframework.transaction.annotation.Transactional;

public interface SubredditService {

    @Transactional
    Subreddit save(Subreddit subreddit);

    @Transactional
    Subreddit subscribeToSubreddit(String subname);

    @Transactional
    void delete(long id);

}
