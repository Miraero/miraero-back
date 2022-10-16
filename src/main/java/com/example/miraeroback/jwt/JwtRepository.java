package com.example.miraeroback.jwt;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JwtRepository extends JpaRepository<RefreshToken, Long> {

    boolean existsByRefreshToken(String accessToken);

    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
