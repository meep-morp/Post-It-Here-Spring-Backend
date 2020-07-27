package com.postit.userdata.services;

import com.postit.userdata.models.Posts;
import com.postit.userdata.repositories.PostsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "postsService")
public class PostServiceImpl implements PostsService{

    @Autowired
    private PostsRepo postsRepo;

    @Override
    @Transactional
    public Posts save(Posts post) {
       return  postsRepo.save(post);
    }
}
