package com.example.microservice.controller;

import com.example.microservice.model.TriviaQuestion;
import com.example.microservice.services.TriviaQuestionService;
import com.example.microservice.services.TriviaQuestionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;

@AutoConfigureMockMvc
@SpringBootTest
public class TriviaQuestionControllerTest {

    @Mock
    private TriviaQuestionServiceImpl tqSvc;

    @InjectMocks
    private TriviaQuestionController triviaQuestionController;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(triviaQuestionController).build();
    }

    @Test
    void getByIdControllerTest() throws Exception{

        when(tqSvc.getQuestionById(0l)).thenReturn(Optional.of(new TriviaQuestion(0,
                        "How many feet are in one mile?",
                        "5260","5270","5280","5290",
                        "C","The altitude of Denver, Colorado",(new Date()))));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/trivia/questions?" + "id=0");
        ///api/v1/trivia/questions?id=0
        this.mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.question").value("How many feet are in one mile?"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.answerA").value("5260"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.correctAnswer").value("C"));
        verify(tqSvc).getQuestionById(0l);
    }

    @Test
    void getRandomControllerTest() throws Exception{

        when(tqSvc.getRandomQuestion() ).thenReturn(new TriviaQuestion(0,
                "How many feet are in one mile?",
                "5260","5270","5280","5290",
                "C","The altitude of Denver, Colorado",(new Date())));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/trivia/questions/random" );
        ///api/v1/trivia/questions?id=0
        this.mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.question").value("How many feet are in one mile?"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.answerA").value("5260"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.correctAnswer").value("C"));
        verify(tqSvc).getRandomQuestion();
    }
    @Test
    void getSpecificControllerTest() throws Exception{

        List<Long> sList = new ArrayList<>(Arrays.asList(0L,1L));
        var expList = new ArrayList<TriviaQuestion>();
        expList.add(new TriviaQuestion(0,
                        "How many feet are in one mile?",
                        "5260","5270","5280","5290",
                        "C","The altitude of Denver, Colorado",(new Date())));
        expList.add(new TriviaQuestion(1,
                "How many countries in the World?",
                "193","195","206","214",
                "B","count by fingers",(new Date())));

        when(tqSvc.getSpecificQuestions(sList)).thenReturn(expList);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/trivia/questions/more?" + "specific=0,1");
        ///api/v1/trivia/questions?id=0
        this.mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].question").value("How many feet are in one mile?"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].answerA").value("5260"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].correctAnswer").value("C"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].question").value("How many countries in the World?"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].answerD").value("214"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].correctAnswer").value("B"));
        verify(tqSvc).getSpecificQuestions(sList);
    }

    @Test
    void getPageControllerTest() throws Exception{

        var expList = new ArrayList<TriviaQuestion>();
        expList.add(new TriviaQuestion(3,
                "How many countries in the World?",
                "193","195","206","214",
                "B","count by fingers",(new Date())));
        expList.add(new TriviaQuestion(4,
                "Which 1979 film included a spaceship called Nostromo?",
                "The Emperor Strikes Back","Alien","The Black Hole","Star Trek: The Motion Picture",
                "D","Not from this world",(new Date())));

        when(tqSvc.getPageofQuestions(3, 1)).thenReturn(expList);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/trivia/questions/list?"+"offset=1");
        ///api/v1/trivia/questions?id=0
        this.mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].question").value("How many countries in the World?"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].answerD").value("214"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].correctAnswer").value("B"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].question").value("Which 1979 film included a spaceship called Nostromo?"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].answerB").value("Alien"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].correctAnswer").value("D"));
        verify(tqSvc).getPageofQuestions(3,1) ;
    }

    @Test
    void getTotalControllerTest() throws Exception{

        when(tqSvc.getTotNumOfQuestion() ).thenReturn(5l);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/trivia/questions/count");
        ///api/v1/trivia/questions?id=0
        this.mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect((MockMvcResultMatchers.content().string("5")));
        verify(tqSvc).getTotNumOfQuestion() ;
    }


}
