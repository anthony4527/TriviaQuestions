package com.example.microservice.services;

import com.example.microservice.model.TriviaQuestion;

import java.util.List;
import java.util.Optional;

public interface TriviaQuestionService {
    public Optional<TriviaQuestion> getQuestionById(long id);

    public List<TriviaQuestion> getPageofQuestions(int pageSize, int offset);

    public List<TriviaQuestion> getSpecificQuestions(List<Long> specific);

    public TriviaQuestion getRandomQuestion();

    public long getTotNumOfQuestion();

}
