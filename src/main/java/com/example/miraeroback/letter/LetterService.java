package com.example.miraeroback.letter;

import com.example.miraeroback.letter.dto.LetterOfCreate;
import com.example.miraeroback.letter.dto.LetterOfGet;
import com.example.miraeroback.letter.dto.LettersOfGet;
import org.springframework.stereotype.Service;

@Service
public interface LetterService {

    void createLetter(LetterOfCreate letterOfCreate);

    LetterOfGet getLetter(Long id);

    LettersOfGet getLetters();

    void deleteLetter(Long id);
}
