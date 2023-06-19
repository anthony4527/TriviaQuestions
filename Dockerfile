FROM openjdk:17

EXPOSE 8080
ADD target/docker-trivia.jar docker-trivia.jar

ENTRYPOINT ["java","-jar","docker-trivia.jar"]
