package com.example.microservice.repository;

import com.example.microservice.model.TriviaQuestion;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class TriviaQuestionRepositoryImp implements TriviaQuestionRepository{

    private List<TriviaQuestion> triviaQuestionList;
    public TriviaQuestionRepositoryImp(){

        this.triviaQuestionList = new ArrayList<TriviaQuestion>();
        //List<TriviaQuestion> questionList = new ArrayList<>();
        this.triviaQuestionList.add((new TriviaQuestion(0,
                "How many feet are in a mile?",
                "5260","5270","5280","5290",
                        "C","The altitude of Denver, Colorado",(new Date()) )));

        this.triviaQuestionList.add((new TriviaQuestion(1,
                "What was the first toy advertised on television?",
                "The Rubix Cube","Mr. Potato Head","Barbie","hula hoop",
                "B","Use your head on this one",(new Date()) )));

        this.triviaQuestionList.add((new TriviaQuestion(2,
                "The martial art of kung fu originated in which country?",
                "Vietnam","United States","Japan","China",
                "D","Name most likely derives from the name of the Qin dynasty",(new Date()) )));

        this.triviaQuestionList.add((new TriviaQuestion(3,
                "Which 1979 film included a spaceship called Nostromo?",
                "The Emperor Strikes Back","Alien","The Black Hole","Star Trek: The Motion Picture",
                "D","Not from this world",(new Date()) )));

        this.triviaQuestionList.add((new TriviaQuestion(4,
                "Which country lies on the border between Spain and France?",
                "Andorra","Luxemborg","England","Portugal",
                "A","Go with your first guess",(new Date()) )));
/*
        questionList.add((new TriviaQuestionBuilder())
                .id(5)
                .question("CERN launched the very first website in what year?")
                .answerA("1985")
                .answerB("1960")
                .answerC("1990")
                .answerD("1995")
                .correctAnswer("C")
                .hint("Not before Star Wars")
                .lastUpdated(new Date())
                .build()
        );
        questionList.add((new TriviaQuestionBuilder())
                .id(6)
                .question("What is the largest animal currently on Earth?")
                .answerA("Elephant")
                .answerB("Polar Bear")
                .answerC("Blue Whale")
                .answerD("Box Jellifish")
                .correctAnswer("C")
                .hint("Stick to the seas")
                .lastUpdated(new Date())
                .build()
        );
        questionList.add((new TriviaQuestionBuilder())
                .id(7)
                .question("What was first feature length animated film?")
                .answerA("Akira")
                .answerB("Snow White and the Seven Dwarfs")
                .answerC("Cinderella")
                .answerD("Bambi")
                .correctAnswer("B")
                .hint("Bad apples")
                .lastUpdated(new Date())
                .build()
        );
        questionList.add((new TriviaQuestionBuilder())
                .id(8)
                .question("The assasination that is said to have lead to World War I, occured in what city?")
                .answerA("Paris")
                .answerB("Sarajevo")
                .answerC("Belgrade")
                .answerD("Rome")
                .correctAnswer("B")
                .hint("Go east")
                .lastUpdated(new Date())
                .build()
        );
        questionList.add((new TriviaQuestionBuilder())
                .id(9)
                .question("World War I flying ace Manfred von Richthofen is known by what nickname?")
                .answerA("Snoopy")
                .answerB("Bob")
                .answerC("The Manchurian Candidate")
                .answerD("The Red Baron")
                .correctAnswer("D")
                .hint("Royalty")
                .lastUpdated(new Date())
                .build()
        );

        questionList.add((new TriviaQuestionBuilder())
                .id(10)
                .question("The Lone Star State is the nickname for which U.S. State?")
                .answerA("California")
                .answerB("Colorado")
                .answerC("Texas")
                .answerD("Alaska")
                .correctAnswer("C")
                .hint("Don't 'mess' this one up")
                .lastUpdated(new Date())
                .build()
        );
*/
    }
    @Override
    public Optional<TriviaQuestion> getQuestionByIdx(long num) {
        var question = new TriviaQuestion();
        if (num>= this.triviaQuestionList.size())
            question = null;
        else
            question = this.triviaQuestionList.get((int)(num));
        Optional<TriviaQuestion> option = Optional.ofNullable(question);

        return option;
    }


    @Override
    public List<TriviaQuestion> getListOfQuestions(int num, int offset) {

        List<TriviaQuestion> page = new ArrayList<>();
        int start = offset * num;
        if (start>= this.triviaQuestionList.size())
            return null;    //use optional
        int count = ( start+num < this.triviaQuestionList.size() )? num : (this.triviaQuestionList.size()- start);

        for (int i= 0; i<count; i++) {
            page.add(this.triviaQuestionList.get(start + i));
        }
        return page;
    }
    @Override
    public long getTotNumOfQuestion() {
        return this.triviaQuestionList.size() ;
    }


}
