package com.example.miraeroback.letter;

import com.example.miraeroback.letter.dto.LetterOfCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class LetterController {
    private final LetterService letterService;

    @PostMapping("/api/letter")
    public void createLetter(@RequestBody LetterOfCreate letterOfCreate){
        letterService.createLetter(letterOfCreate);
    }

    @GetMapping("/api/letter/{id}")
    public ResponseEntity<?> getLetter(@PathVariable Long id){
        return new ResponseEntity<>(letterService.getLetter(id), HttpStatus.OK);
    }

    @GetMapping("/api/letters")
    public ResponseEntity<?> getLetters(){
        return new ResponseEntity<>(letterService.getLetters(), HttpStatus.OK);
    }

    @DeleteMapping("/api/logout/{letterId}")
    public ResponseEntity<?> deleteLetters(@PathVariable(name = "letterId") Long letterId){
        letterService.deleteLetter(letterId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
