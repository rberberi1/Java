package com.rosela.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rosela.quizapp.dao.QuestionDao;
import com.rosela.quizapp.model.Questions;

@Service
public class QuestionService {
  @Autowired
  QuestionDao questionDao;

  public ResponseEntity< List<Questions>> getAllQuestions() {
    try{
     return new ResponseEntity<>( questionDao.findAll(), HttpStatus.OK);
    }catch (Exception e){
      e.printStackTrace();
    }
    return new ResponseEntity<>( new ArrayList<>(), HttpStatus.BAD_REQUEST);
  }

  public ResponseEntity<List<Questions>> getQuestionsByCategory(String category) {
    try{
      return new ResponseEntity<>( questionDao.findByCategory(category), HttpStatus.OK);
    }catch(Exception e){
      e.printStackTrace();
    }
    return new ResponseEntity<>( new ArrayList<>(), HttpStatus.BAD_REQUEST);
  }

  public ResponseEntity<String> addQuestion(Questions question) {
    questionDao.save(question);
    return new ResponseEntity<>("success", HttpStatus.CREATED);
  }

}
