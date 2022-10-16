package com.example.miraeroback.letter.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LetterOfCreated {

    private Long id;
    private String title;
    private String content;
}
