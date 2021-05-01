package com.meng.blog.dto;

import com.meng.blog.model.VoteType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class VoteDto {
	private VoteType voteType;
    private Long postId;
	public VoteDto() {
		
	}
	public VoteDto(VoteType voteType, Long postId) {
		super();
		this.voteType = voteType;
		this.postId = postId;
	}
	public VoteType getVoteType() {
		return voteType;
	}
	public void setVoteType(VoteType voteType) {
		this.voteType = voteType;
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	
	
	
}
