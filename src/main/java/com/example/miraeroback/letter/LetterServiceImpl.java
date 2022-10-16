package com.example.miraeroback.letter;

import com.example.miraeroback.letter.dto.LetterOfCreate;
import com.example.miraeroback.letter.dto.LetterOfGet;
import com.example.miraeroback.letter.dto.LettersOfGet;
import com.example.miraeroback.user.User;
import com.example.miraeroback.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class LetterServiceImpl implements LetterService{

    private final UserService userService;

    private final LetterRepository letterRepository;

    // 편지 읽기
    @Override
    public void createLetter(LetterOfCreate letterOfCreate,  Long userId) {
        User userEntity = userService.getUserEntity(userId);
        letterRepository.save(
                Letter.builder()
                        .title(letterOfCreate.getTitle())
                        .content(letterOfCreate.getContent())
                        .receiveDate(letterOfCreate.getReceiveDate())
                        .user(userEntity)
                        .build()
        );

    }

    // 편지 1개 조회
    @Override
    public LetterOfGet getLetter(Long letterId, Long userId) {

        Letter letterEntity = letterRepository.findByIdAndUser
                        (letterId, userService.getUserEntity(userId))
                .orElseThrow(() -> new RuntimeException("메세지 없어요~"));

        letterEntity.updateReadDate();

        return LetterOfGet.builder()
                .content(letterEntity.getContent())
                .title(letterEntity.getTitle())
                .receiveDate(letterEntity.getReceiveDate())
                .createdAt(letterEntity.getCreatedAt())
                .lastReadDate(letterEntity.getLastReadDate())
                .build();
    }

    // 편지 모두 조회
    @Override
    public LettersOfGet getLetters(Long userId) {
        User userEntity = userService.getUserEntity(userId);
        List<Letter> letterEntities = letterRepository.findAllByUser(userEntity);

        return LettersOfGet.builder()
                .letters(letterEntities.stream()
                        .map(v ->
                            LetterOfGet.builder()
                                    .receiveDate(v.getReceiveDate())
                                    .title(v.getTitle())
                                    .content(v.getContent())
                                    .createdAt(v.getCreatedAt())
                                    .lastReadDate(v.getLastReadDate())
                                    .build()
                        )
                        .collect(Collectors.toList()))
                .build();

    }
    // 편지 삭제
    @Override
    public void deleteLetter(Long id, Long userId) {
        letterRepository.deleteById(id);
    }
}
