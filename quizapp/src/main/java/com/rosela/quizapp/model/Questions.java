package com.rosela.quizapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name= "questions")
public class Questions {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String category;
  private String difficultylevel;
  private String question;
  private String option1;
  private String option2;
  private String option3;
  private String rightanswer;
  
}
