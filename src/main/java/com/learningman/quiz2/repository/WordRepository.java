package com.learningman.quiz2.repository;

import com.learningman.quiz2.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.ArrayList;

public interface WordRepository extends CrudRepository<Word, Long> {
    @Override
    ArrayList<Word> findAll();
}
