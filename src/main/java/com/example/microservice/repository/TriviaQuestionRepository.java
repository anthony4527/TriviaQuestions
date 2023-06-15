package com.example.microservice.repository;

import com.example.microservice.model.TriviaQuestion;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TriviaQuestionRepository {

    public Optional<TriviaQuestion> getQuestionByIdx(long num);
    public List<TriviaQuestion> getListOfQuestions(int pageSize, int offset);

    public long getTotNumOfQuestion();

}
