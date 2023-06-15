package com.example.microservice.exception;

public class TriviaQuestionException extends RuntimeException {
    public TriviaQuestionException(String message){
        super(message);
    }
}
