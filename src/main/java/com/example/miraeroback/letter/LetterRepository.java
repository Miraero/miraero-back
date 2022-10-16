package com.example.miraeroback.letter;

import com.example.miraeroback.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LetterRepository extends JpaRepository<Letter, Long> {

    List<Letter> findAllByUser(User user);

    Optional<Letter> findByIdAndUser(Long letterId, User user);
}
