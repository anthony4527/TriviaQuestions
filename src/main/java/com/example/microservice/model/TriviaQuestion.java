package com.example.microservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
//@NoArgsConstructor

final public class TriviaQuestion {
        private long id;
        private String question;
        private String answerA;
        private String answerB;
        private String answerC;
        private String answerD;
        private String correctAnswer;
        private String hint;
        private Date lastUpdated;

}
