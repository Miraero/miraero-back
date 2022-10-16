package com.example.miraeroback.letter;

import com.example.miraeroback.letter.dto.LetterOfCreate;
import com.example.miraeroback.letter.dto.LetterOfGet;
import com.example.miraeroback.letter.dto.LettersOfGet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class LetterServiceImpl implements LetterService{

    private final LetterRepository letterRepository;

    // 편지 읽기
    @Override
    public void createLetter(LetterOfCreate letterOfCreate) {

        Letter letterEntity = letterRepository.save(
                Letter.builder()
                        .title(letterOfCreate.getTitle())
                        .content(letterOfCreate.getContent())
                        .receiveDate(letterOfCreate.getReceiveDate())
                        .build()
        );

    }

    // 편지 1개 조회
    @Override
    public LetterOfGet getLetter(Long id) {
        Letter letterEntity = letterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("메세지 없어요~"));

        return LetterOfGet.builder()
                .content(letterEntity.getContent())
                .title(letterEntity.getTitle())
                .receiveDate(letterEntity.getReceiveDate())
                .build();
    }

    // 편지 모두 조회
    @Override
    public LettersOfGet getLetters() {
//        List<Letter> letterEntities = letterRepository.findAllByUser(1L);
//
//        return LettersOfGet.builder()
//                .letters(letterEntities.stream()
//                        .map(v ->
//                            LetterOfGet.builder()
//                                    .receiveDate(v.getReceiveDate())
//                                    .title(v.getTitle())
//                                    .content(v.getContent())
//                                    .build()
//                        )
//                        .collect(Collectors.toList()))
//                .build();

        return null;
    }
    // 편지 삭제
    @Override
    public void deleteLetter(Long id) {
        letterRepository.deleteById(id);
    }
}
