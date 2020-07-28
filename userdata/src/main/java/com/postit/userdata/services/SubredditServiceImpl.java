package com.postit.userdata.services;

import com.postit.userdata.exceptions.ResourceNotFoundException;
import com.postit.userdata.models.SavedPosts;
import com.postit.userdata.models.Subreddit;
import com.postit.userdata.models.UserSubs;
import com.postit.userdata.repositories.SubredditRepo;
import com.postit.userdata.repositories.UserRepo;
import com.pusher.client.Pusher;
import com.pusher.client.channel.Channel;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;
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

        newSub.getSavedposts().clear();
        for ( SavedPosts u : subreddit.getSavedposts() ) {
            newSub.getSavedposts().add(new SavedPosts(u.getSubreddit() ,u.getPosts()));
        }

        return subredditRepo.save(subreddit);
    }

    @Override //todo
    public Subreddit subscribeToSubreddit(String subname) {
        Pusher pusher = new Pusher("50ed18dd967b455393ed");

        pusher.connect(new ConnectionEventListener() {
            @Override
            public void onConnectionStateChange(ConnectionStateChange change) {
                System.out.println("State changed to " + change.getCurrentState() +
                        " from " + change.getPreviousState());
            }

            @Override
            public void onError(String message, String code, Exception e) {
                System.out.println("There was a problem connecting!");
            }
        }, ConnectionState.ALL);

        Channel channel = pusher.subscribe(subname);
        channel.bind("new-listing", event -> System.out.println("Received event with data: " + event.toString()));
        pusher.disconnect();
        pusher.connect();

        return null;
    }

    @Override
    @Transactional
    public void delete(long id) {
        subredditRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Subreddit %s not found", id)));
        subredditRepo.deleteById(id);
    }
}
