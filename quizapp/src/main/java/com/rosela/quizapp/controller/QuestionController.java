package com.rosela.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rosela.quizapp.model.Questions;
import com.rosela.quizapp.service.QuestionService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("question")
public class QuestionController {

  @Autowired
  QuestionService questionService;

  @GetMapping("allQuestions")
  public ResponseEntity<List<Questions>> getAllQuestions(){
    return questionService.getAllQuestions();
  }

  @GetMapping("category/{category}")
  public ResponseEntity<List<Questions>> getQuestionsByCategory (@PathVariable String category){
    return questionService.getQuestionsByCategory(category);
  }

  @PostMapping("add")
  public String addQuestion(@RequestBody Questions question){
      questionService.addQuestion(question);
      return "success";
  }
}
