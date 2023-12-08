package com.learningman.quiz2.api;

import com.learningman.quiz2.dto.WordForm;
import com.learningman.quiz2.entity.Word;
import com.learningman.quiz2.repository.WordRepository;
import com.learningman.quiz2.service.WordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class WordApiController {
    @Autowired
    private WordService wordService;
    // GET
    @GetMapping("/api/words")
    public List<Word> index(){
        return wordService.index();
    }

    @GetMapping("/api/words/{id}")
    public Word show(@PathVariable Long id){
        return wordService.show(id);
    }

    // POST
    @PostMapping("/api/words")
    public ResponseEntity<Word> create(@RequestBody WordForm dto){
        Word creaed = wordService.create(dto);
        return (creaed != null) ?
            ResponseEntity.status(HttpStatus.OK).body(creaed) :
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH
    @PatchMapping("/api/words/{id}")
    public ResponseEntity<Word> update(@PathVariable Long id, @RequestBody WordForm dto){
        Word updated = wordService.update(id, dto);
        return (updated != null) ?
            ResponseEntity.status(HttpStatus.OK).body(updated):
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE
    @DeleteMapping("/api/words/{id}")
    public ResponseEntity<Word> delete (@PathVariable Long id){
        Word deleted = wordService.delete(id);
        return (deleted != null) ?
            ResponseEntity.status(HttpStatus.NO_CONTENT).build():
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
