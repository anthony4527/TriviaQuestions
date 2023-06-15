package com.example.microservice.services;

import com.example.microservice.exception.TriviaQuestionException;
import com.example.microservice.model.TriviaQuestion;
import com.example.microservice.repository.TriviaQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class TriviaQuestionServiceImpl implements TriviaQuestionService{

    @Autowired
    private TriviaQuestionRepository triviaQuestionRepository;
    @Override
    public Optional<TriviaQuestion> getQuestionById(long id) {
        if (!triviaQuestionRepository.getQuestionByIdx( id ).isPresent())
            throw (new TriviaQuestionException("No question with the specified Id"));
        else
        return triviaQuestionRepository.getQuestionByIdx( id );
    }

    @Override
    public List<TriviaQuestion> getPageofQuestions(int pageSize, int offset) {
        return triviaQuestionRepository.getListOfQuestions(pageSize, offset ) ;
    }

    @Override
    public List<TriviaQuestion> getSpecificQuestions(List<Long> specific) {
        List<TriviaQuestion> lisQ = new ArrayList<>();
        for (long l : specific){
            TriviaQuestion question = triviaQuestionRepository.getQuestionByIdx(l)
                    .orElseThrow(()-> new TriviaQuestionException("question no."+String.valueOf(l)+" does not exist"));
            lisQ.add(question);
        }
        return lisQ;
    }

    @Override
    public TriviaQuestion getRandomQuestion() {
        long rand = (long) Math.floor( Math.random() * triviaQuestionRepository.getTotNumOfQuestion() );
        TriviaQuestion random = triviaQuestionRepository.getQuestionByIdx(rand ).get();
        return random;
    }

    @Override
    public long getTotNumOfQuestion() {
        return  triviaQuestionRepository.getTotNumOfQuestion();
    }
}
