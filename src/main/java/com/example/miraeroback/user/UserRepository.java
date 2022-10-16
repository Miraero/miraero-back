package com.example.miraeroback.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByKakaoId(long kakaoId);
    Optional<User> findByKakaoId(long kakaoId);
}
