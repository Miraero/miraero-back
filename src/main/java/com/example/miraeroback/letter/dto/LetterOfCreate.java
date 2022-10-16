package com.example.miraeroback.letter.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class LetterOfCreate {

    private String title;
    private String content;
    private LocalDateTime receiveDate;


}
