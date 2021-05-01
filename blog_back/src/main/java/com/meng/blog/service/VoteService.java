package com.meng.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meng.blog.dto.VoteDto;
import com.meng.blog.exception.PostNotFoundException;
import com.meng.blog.exception.SpringRedditException;
import com.meng.blog.model.Post;
import com.meng.blog.model.Vote;
import com.meng.blog.repo.PostRepository;
import com.meng.blog.repo.VoteRepository;

import lombok.AllArgsConstructor;

import java.util.Optional;

import static com.meng.blog.model.VoteType.UPVOTE;

@Service
@AllArgsConstructor
public class VoteService {

	@Autowired
    private VoteRepository voteRepository;
	@Autowired
    private PostRepository postRepository;
	@Autowired
    private AuthService authService;

    @Transactional
    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + voteDto.getPostId()));
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
        if (voteByPostAndUser.isPresent() &&
                voteByPostAndUser.get().getVoteType()
                        .equals(voteDto.getVoteType())) {
            throw new SpringRedditException("You have already "
                    + voteDto.getVoteType() + "'d for this post");
        }
        if (UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }
        voteRepository.save(mapToVote(voteDto, post));
        postRepository.save(post);
    }

    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }
}