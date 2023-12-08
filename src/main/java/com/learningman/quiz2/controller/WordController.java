package com.learningman.quiz2.controller;

import com.learningman.quiz2.dto.CommentDto;
import com.learningman.quiz2.dto.WordForm;
import com.learningman.quiz2.entity.Word;
import com.learningman.quiz2.repository.WordRepository;
import com.learningman.quiz2.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WordController {
    @Autowired
    private final WordRepository wordRepository;
    @Autowired
    private CommentService commentService; // 서비스 객체 주입

    @GetMapping("/words/new")
    public String newWordForm(){
        return "words/new";
    }

    @PostMapping("/words/create")
    public String createWord(WordForm form){
        log.info(form.toString());
        // 1. DTO를 엔티티로 변환
        Word word = form.toEntity();
        log.info(word.toString());
        // 2. 리파지토리로 엔티티를 DB에 저장
        Word saved = wordRepository.save(word);
        log.info(saved.toString());
        return "redirect:/words/" + saved.getId();
    }

    @GetMapping("/words/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);
        // 1. id를 조회해 데이터 가져오기
        Word wordEntity = wordRepository.findById(id).orElse(null);
        List<CommentDto> commentsDtos = commentService.comments(id);
        // 2. 모델에 데이터 등록하기
        model.addAttribute("word", wordEntity);
        model.addAttribute("commentDtos", commentsDtos);
        // 3. 뷰 페이지 반환하기
        return "words/show";
    }

    @GetMapping("/words")
    public String index(Model model){
        // 1. 모든 데이터 가져오기
        List<Word> wordEntityList = wordRepository.findAll();
        // 2. 모델에 데이터 등록하기
        model.addAttribute("wordList", wordEntityList);
        // 3. 뷰 페이지 설정하기
        return "words/index";
    }

    @GetMapping("/words/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        // 수정할 데이터 가져오기
        Word wordEntity = wordRepository.findById(id).orElse(null);
        // 모델에 데이터 등록하기
        model.addAttribute("word", wordEntity);
        // 뷰 페이지 설정하기
        return "words/edit";
    }

    @PostMapping("/words/update")
    public String update(WordForm form) {
        log.info(form.toString());
        // 1. DTO를 엔티티로 변환하기
        Word wordEntity = form.toEntity();
        log.info(wordEntity.toString());
        // 2. 엔티티를 DB에 저장하기
        // 2-1. DB에서 기존 데이터 가져오기
        Word target = wordRepository.findById(wordEntity.getId()).orElse(null);
        // 2-2. 기존 데이터 값을 갱신하기
        if (target != null) {
            wordRepository.save(wordEntity); // 엔티티를 DB에 저장(갱신)
        }
        // 3. 수정 결과 페이지로 리다이렉트하기
        return "redirect:/words/" + wordEntity.getId();
    }

    @GetMapping("/words/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        log.info("삭제 요청이 들어왔습니다!!");
        // 1. 삭제할 대상 가져오기
        Word target = wordRepository.findById(id).orElse(null);
        log.info(target.toString());
        // 2. 대상 엔티티 삭제하기
        if (target != null) {
            wordRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제됐습니다!");
        }
        // 3. 결과 페이지로 리다이렉트하기
        return "redirect:/words";
    }
}
