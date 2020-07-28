package com.postit.userdata.services;

import com.postit.userdata.models.Subreddit;

public interface SubredditService {

    Subreddit save(Subreddit subreddit);

    void delete(long id);

}
