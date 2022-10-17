package com.example.miraeroback.letter.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class LetterOfGet {
    private Long id;
    private String title;
    private String content;
    private String font;
    private String letterType;
    private LocalDateTime receiveDate;
    private LocalDateTime createdAt;
    private LocalDateTime lastReadDate = null;
}
