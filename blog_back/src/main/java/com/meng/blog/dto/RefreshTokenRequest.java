package com.meng.blog.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenRequest {
	@NotBlank
	private String refreshToken;
	private String username;
	public RefreshTokenRequest(@NotBlank String refreshToken, String username) {
		super();
		this.refreshToken = refreshToken;
		this.username = username;
	}
	public RefreshTokenRequest() {
		
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
