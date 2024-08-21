package com.rosela.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rosela.quizapp.model.QuestionWrapper;
import com.rosela.quizapp.model.Response;
import com.rosela.quizapp.service.QuizService;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("quiz")
public class QuizContoller {

  @Autowired
  QuizService quizService;

  @PostMapping("create")
  public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
      
        return quizService.createQuiz(category, numQ, title);

  }

  @GetMapping("get/{id}")
  public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
    return quizService.getQuizQuestions(id);
  }

  @PostMapping("submit/{id}")
  public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
      return quizService.calculateResult(id,responses );
  }
  
}
