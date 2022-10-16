package com.example.miraeroback.letter.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class LettersOfGet {

    private List<LetterOfGet> letters;
}
