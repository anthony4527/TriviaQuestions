FROM openjdk:17

RUN mkdir /app

COPY docker-trivia.jar /app/docker-trivia.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","docker-trivia.jar"]