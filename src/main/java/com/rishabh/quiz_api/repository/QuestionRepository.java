package com.rishabh.quiz_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishabh.quiz_api.models.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    public Question getQuestionById(long id);
}
