package com.example.miraeroback.letter;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LetterRepository extends JpaRepository<Letter, Long> {

    //List<Letter> findAllByUser(Long id);
}
