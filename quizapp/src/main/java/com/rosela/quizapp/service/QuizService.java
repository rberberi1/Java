package com.rosela.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rosela.quizapp.dao.QuestionDao;
import com.rosela.quizapp.dao.QuizDao;
import com.rosela.quizapp.model.QuestionWrapper;
import com.rosela.quizapp.model.Questions;
import com.rosela.quizapp.model.Quiz;
import com.rosela.quizapp.model.Response;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    @PersistenceContext
    private EntityManager entityManager;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Questions> questions = findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }

   
    @SuppressWarnings("unchecked")
    public List<Questions> findRandomQuestionsByCategory(String category, int numQ) {
        String sql = "SELECT * FROM questions q WHERE q.category = :category ORDER BY RANDOM() LIMIT " + numQ;
        Query query = entityManager.createNativeQuery(sql, Questions.class);
        query.setParameter("category", category);
        return query.getResultList();
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz= quizDao.findById(id);
        List<Questions> questionsFromDB=quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser= new ArrayList<>();
        for(Questions q: questionsFromDB){
            QuestionWrapper qw=new QuestionWrapper(q.getId(), q.getQuestion(), q.getOption1(), q.getOption2(), q.getOption3());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }


    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
      Quiz quiz=quizDao.findById(id).get();
      List<Questions> questions=quiz.getQuestions();
      int right=0;
      int i=0;
      for(Response response:responses){
        if(response.getResponse().equals(questions.get(i).getRightanswer()))
            right++;
        i++;
      }
      return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
