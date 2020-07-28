package com.postit.userdata.services;

import com.postit.userdata.exceptions.ResourceNotFoundException;
import com.postit.userdata.models.Subreddit;
import com.postit.userdata.models.UserSubs;
import com.postit.userdata.repositories.SubredditRepo;
import com.postit.userdata.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "subredditService")
public class SubredditServiceImpl implements SubredditService{

    @Autowired
    private SubredditRepo subredditRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    @Transactional
    public Subreddit save(Subreddit subreddit) {
        Subreddit newSub = new Subreddit();

        if ( subreddit.getSubid() != 0 ) {
            subredditRepo.findById(subreddit.getSubid())
                    .orElseThrow(() -> new ResourceNotFoundException("Subreddit not found"));
            newSub.setSubid(subreddit.getSubid());
        }
        newSub.setTitle(subreddit.getTitle());
        newSub.setDescription(subreddit.getDescription());

        newSub.getUsersubs().clear();
        for ( UserSubs u : subreddit.getUsersubs() ) {
            newSub.getUsersubs().add(new UserSubs(userRepo.findById(u.getUser().getUserid())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"))
                    ,u.getSubreddit()));
        }

        return subredditRepo.save(newSub);
    }

    @Override
    public void delete(long id) {
        subredditRepo.deleteById(id);
    }
}
