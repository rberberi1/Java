package com.rosela.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rosela.quizapp.model.Quiz;

public interface QuizDao extends JpaRepository <Quiz, Integer>{

}
