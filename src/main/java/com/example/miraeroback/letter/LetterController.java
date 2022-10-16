package com.example.miraeroback.letter;

import com.example.miraeroback.jwt.model.AuthUser;
import com.example.miraeroback.letter.dto.LetterOfCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class LetterController {
    private final LetterService letterService;

    @PostMapping("/api/letter")
    public void createLetter(@AuthenticationPrincipal AuthUser authUser, @RequestBody LetterOfCreate letterOfCreate){
        letterService.createLetter(letterOfCreate, Long.parseLong(authUser.getId()));
    }

    @GetMapping("/api/letter/{id}")
    public ResponseEntity<?> getLetter(@AuthenticationPrincipal AuthUser authUser, @PathVariable Long id){
        return new ResponseEntity<>(letterService.getLetter(id, Long.parseLong(authUser.getId())), HttpStatus.OK);
    }

    @GetMapping("/api/letters")
    public ResponseEntity<?> getLetters(@AuthenticationPrincipal AuthUser authUser){
        return new ResponseEntity<>(letterService.getLetters(Long.parseLong(authUser.getId())), HttpStatus.OK);
    }

    @DeleteMapping("/api/logout/{letterId}")
    public ResponseEntity<?> deleteLetters(@AuthenticationPrincipal AuthUser authUser, @PathVariable(name = "letterId") Long letterId){
        letterService.deleteLetter(letterId, Long.parseLong(authUser.getId()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
