package com.meng.blog.dto;

import com.meng.blog.model.VoteType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto {
	private VoteType voteType;
    private Long postId;
}
