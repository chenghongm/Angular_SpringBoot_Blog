package com.meng.blog.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meng.blog.model.Post;
import com.meng.blog.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long>{
	Optional<Vote> findByPostAndUserOrderByVoteIdesc(Post post);
}
