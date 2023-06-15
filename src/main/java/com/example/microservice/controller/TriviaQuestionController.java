package com.example.microservice.controller;

import com.example.microservice.model.TriviaQuestion;
import com.example.microservice.services.TriviaQuestionService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/api/v1/trivia/questions")

public class TriviaQuestionController {

    public final int PAGE_SIZE = 3;
    @Autowired
    private TriviaQuestionService triviaQuestionService;

    @GetMapping()
    public ResponseEntity<Optional<TriviaQuestion>> getById(@RequestParam String id ){
        Optional<TriviaQuestion> question = triviaQuestionService.getQuestionById(Long.parseLong(id));
        return new ResponseEntity<> (question, HttpStatus.OK);
    }

    @GetMapping("/random")
    public ResponseEntity<TriviaQuestion> getRandom ( ){
        TriviaQuestion question = triviaQuestionService.getRandomQuestion() ;
        return new ResponseEntity<> (question, HttpStatus.OK);
    }
    @GetMapping("/more")
    public ResponseEntity<List<TriviaQuestion>> getQuestionBySpecific(@RequestParam String specific ){
        String[] specificId = specific.split(",");
        List<Long> moreId = new ArrayList<>();
        for (String s : specificId){
            moreId.add(Long.parseLong(s));
        }
        List<TriviaQuestion> setOfQuestions = triviaQuestionService.getSpecificQuestions(moreId);
        return new ResponseEntity<> (setOfQuestions, HttpStatus.OK);
    }


    @GetMapping("/count")
    public ResponseEntity<String> getCount( ){
        String total = String.valueOf(triviaQuestionService.getTotNumOfQuestion() );
        return new ResponseEntity<> (total, HttpStatus.OK);
    }
    @GetMapping("/list")
    public ResponseEntity<List<TriviaQuestion>> getList(@RequestParam(defaultValue = "0") String offset ){
        List<TriviaQuestion> range = triviaQuestionService.getPageofQuestions( PAGE_SIZE, Integer.parseInt(offset));
        return new ResponseEntity<> (range, HttpStatus.OK);
    }
//http://localhost:8080/swagger-ui/index.html#/trivia-question-controller

}
