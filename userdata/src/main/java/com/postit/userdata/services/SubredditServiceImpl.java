package com.postit.userdata.services;

import com.postit.userdata.exceptions.ResourceNotFoundException;
import com.postit.userdata.models.*;
import com.postit.userdata.repositories.SubredditRepo;
import com.postit.userdata.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Objects;


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

    @Override
    public RedditApi getPosts(String subname) {
        String apiUrl = "https://www.reddit.com/r/" + subname + ".json?limit=5";
        RedditApi newRed = new RedditApi();

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        restTemplate.getMessageConverters().add(converter);

        ParameterizedTypeReference<RedditApi> responseType = new ParameterizedTypeReference<>(){};
        ResponseEntity<RedditApi> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET,null,responseType);

        newRed.setData(Objects.requireNonNull(responseEntity.getBody()).getData());
        newRed.setKind(responseEntity.getBody().getKind());
        return newRed;
    }

    @Override
    @Transactional
    public void delete(long id) {
        subredditRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Subreddit %s not found", id)));
        subredditRepo.deleteById(id);
    }
}
