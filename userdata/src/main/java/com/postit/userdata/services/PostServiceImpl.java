package com.postit.userdata.services;

import com.postit.userdata.exceptions.ResourceNotFoundException;
import com.postit.userdata.models.Posts;
import com.postit.userdata.models.PostsRec;
import com.postit.userdata.models.Recommendations;
import com.postit.userdata.models.SavedPosts;
import com.postit.userdata.repositories.PostsRepo;
import com.postit.userdata.repositories.SubredditRepo;
import org.h2.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service(value = "postsService")
public class PostServiceImpl implements PostsService{

    @Autowired
    private PostsRepo postsRepo;

    @Autowired
    private SubredditRepo subrepd;

    @Override
    @Transactional
    public Posts save(Posts post) {
       Posts newPost = new Posts();
       if (post.getPostid() != 0) {
           postsRepo.findById(post.getPostid())
                   .orElseThrow(() -> new ResourceNotFoundException(String.format("Post %s not found.", post.getPostid())));

           newPost.setPostid(post.getPostid());
       }
       newPost.setTitle(post.getTitle());
       newPost.setSelftext(post.getSelftext());
       newPost.setUrl(post.getUrl());

       newPost.getSavedposts().clear();
        for (SavedPosts sp : post.getSavedposts()) {
            newPost.getSavedposts().add(new SavedPosts(subrepd.findById(sp.getSubreddit().getSubid())
                    .orElseThrow(() -> new ResourceNotFoundException("Subreddit not found")), sp.getPosts()));
        }

        return  postsRepo.save(post);
    }

    @Override
    public Recommendations findSubreddits(long num, Posts posts) {
        String apiUrl = "https://post-it-herr.herokuapp.com/predict";
        Recommendations newRec = new Recommendations();

        PostsRec req = new PostsRec(num, posts.getTitle(), posts.getSelftext());

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        restTemplate.getMessageConverters().add(converter);

        ParameterizedTypeReference<Recommendations> responseType = new ParameterizedTypeReference<>(){};
        Recommendations responseEntity = restTemplate.postForObject(apiUrl, req, Recommendations.class);

        newRec.setRecommendations(responseEntity.getRecommendations());

        return newRec;
    }

    @Override
    public List<Posts> search(String title) {
        return postsRepo.findByTitleContainingIgnoreCase(title);
    }
}
