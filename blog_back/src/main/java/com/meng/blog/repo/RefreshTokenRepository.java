package com.meng.blog.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meng.blog.model.RefreshToken;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
	Optional<RefreshToken> findByToken(String token);
	void deleteByToken(String token);
}
