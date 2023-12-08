package com.learningman.quiz2.dto;

import com.learningman.quiz2.entity.Word;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class WordForm {
    private Long id;
    private String kWord;
    private String eWord;

    public Word toEntity() {
        return new Word(id, kWord, eWord);
    }
}
