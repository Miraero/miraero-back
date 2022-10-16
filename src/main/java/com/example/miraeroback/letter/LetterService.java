package com.example.miraeroback.letter;

import com.example.miraeroback.jwt.model.AuthUser;
import com.example.miraeroback.letter.dto.LetterOfCreate;
import com.example.miraeroback.letter.dto.LetterOfGet;
import com.example.miraeroback.letter.dto.LettersOfGet;
import org.springframework.stereotype.Service;

@Service
public interface LetterService {

    void createLetter(LetterOfCreate letterOfCreate, Long userId);

    LetterOfGet getLetter(Long letterId, Long userId);

    LettersOfGet getLetters(Long userId);

    void deleteLetter(Long id, Long userId);
}
