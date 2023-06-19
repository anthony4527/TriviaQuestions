FROM openjdk:17

RUN mkdir /app

COPY docker-trivia.jar /app/docker-trivia.jar
WORKDIR /app
EXPOSE 8080

ENTRYPOINT ["java","-jar","docker-trivia.jar"]
