package com.postit.userdata.repositories;

import com.postit.userdata.models.Subreddit;
import org.springframework.data.repository.CrudRepository;

public interface SubredditRepo extends CrudRepository<Subreddit, Long> {
}
