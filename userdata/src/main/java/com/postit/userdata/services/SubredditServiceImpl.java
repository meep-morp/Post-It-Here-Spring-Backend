package com.postit.userdata.services;

import com.postit.userdata.models.Subreddit;
import com.postit.userdata.repositories.SubredditRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "subredditService")
public class SubredditServiceImpl implements SubredditService{

    @Autowired
    private SubredditRepo subredditRepo;

    @Override
    public Subreddit save(Subreddit subreddit) {
        return subredditRepo.save(subreddit);
    }
}
