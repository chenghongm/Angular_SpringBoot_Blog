package com.meng.blog.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meng.blog.model.Subreddit;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit, Long>{
	Optional<Subreddit> findByName(String subredditName);
}
