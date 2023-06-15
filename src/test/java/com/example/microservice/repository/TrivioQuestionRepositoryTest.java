package com.example.microservice.repository;

import com.example.microservice.model.TriviaQuestion;
import com.example.microservice.services.TriviaQuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TrivioQuestionRepositoryTest {

    @Autowired
    TriviaQuestionRepositoryImp trRepos;
    @Test
    public void getByIdTest(){
        trRepos = new TriviaQuestionRepositoryImp();
        String questionString = trRepos.getQuestionByIdx(0).get().getQuestion();
        assertEquals( "How many feet are in a mile?", questionString);

        Optional emptyL = trRepos.getQuestionByIdx(10);
        assertFalse(emptyL.isPresent());
    }

    @Test
    public void getListOfQuestionTest(){
        trRepos = new TriviaQuestionRepositoryImp();
        List<TriviaQuestion> lisQ =trRepos.getListOfQuestions(2,0);
        assertEquals(2, lisQ.size());
        String secondQuestion = lisQ.get(1).getQuestion();
        assertEquals( "What was the first toy advertised on television?", secondQuestion);

        List<TriviaQuestion> lisQ2 =trRepos.getListOfQuestions(2,1);
        String thirdQuestion = lisQ2.get(0).getQuestion();
        assertEquals( "The martial art of kung fu originated in which country?", thirdQuestion);

        List<TriviaQuestion> lisQ3 =trRepos.getListOfQuestions(2,2);
        assertEquals(2, lisQ3.size());

    }

    @Test
    public void getTotalTest(){
        trRepos = new TriviaQuestionRepositoryImp();
        assertEquals( 6, trRepos.getTotNumOfQuestion());
    }
}
