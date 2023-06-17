package com.example.microservice.services;

import com.example.microservice.model.TriviaQuestion;
import com.example.microservice.repository.TriviaQuestionRepository;
import com.example.microservice.repository.TriviaQuestionRepositoryImp;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TriviaQuestionServiceTest {



    @Mock
    private TriviaQuestionRepository mockRepos;

    @BeforeEach
    public void createMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    private TriviaQuestionServiceImpl trSvc;
    @Test
    public void getQuestionByIdTest(){
        //mockRepos = new TriviaQuestionRepositoryImp();
        when(mockRepos.getQuestionByIdx(0))
                .thenReturn(Optional.of( new TriviaQuestion(0,
                "How many feet are in one mile?",
                "5260","5270","5280","5290",
                "C","The altitude of Denver, Colorado",(new Date())))
                            );
        String questionString = trSvc.getQuestionById(0L).get().getQuestion();
        assertEquals( "How many feet are in one mile?", questionString);
    }

    @Test
    public void getQuestionPageTest(){
        when(mockRepos.getListOfQuestions (2,0))
                .thenReturn( new ArrayList<TriviaQuestion>( Arrays.asList(
                        new TriviaQuestion (0,
                                "How many feet are in one mile?",
                                "5260","5270","5280","5290",
                                "C","The altitude of Denver, Colorado",(new Date())),
                        new TriviaQuestion (1,
                                "How many feet are in a mile?",
                                "5260","5270","5280","5290",
                                "C","The altitude of Denver, Colorado",(new Date()))
                )));

        var questionList = trSvc.getPageofQuestions(2,0);
        assertEquals( 2, questionList.size());
    }
}
