package com.example.miraeroback.letter;

import com.example.miraeroback.letter.auditing.LetterBaseEntity;
import com.example.miraeroback.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Letter extends LetterBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "RECEIVE_DATE")
    private LocalDateTime receiveDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "LAST_READ_DATE")
    private LocalDateTime lastReadDate;

    @Column(name = "FONT")
    private String font;

    @Column(name = "LETTER_TYPE")
    private String letterType;

    public void updateReadDate(){
        this.lastReadDate = LocalDateTime.now();
    }
}
