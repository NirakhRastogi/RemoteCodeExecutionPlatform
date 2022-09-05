# REMOTE CODE Execution platform

## Design Diagram
![Design Diagram](/flowdiagram.png)

## TechStack
1. Springboot v2.4.3
2. Java 8
3. Kafka
4. Postgres DB
5. Docker

## Read More About,

1. Springboot sleuth - https://spring.io/projects/spring-cloud-sleuth
2. Java 8 - https://openjdk.java.net/projects/jdk/8/
4. Postgres - https://www.postgresql.org/
5. Kafka - https://kafka.apache.org/
6. Docker - https://www.docker.com/

# Endpoints

## To submit program
```
curl --location --request POST 'http://localhost:8080/v1/program' \
--header 'X-USER-ID: user1' \
--header 'X-PROGRAM-ID: program1' \
--header 'Content-Type: application/json' \
--data-raw '{
"code": "cHVibGljIFN0cmluZyBteU5hbWUoKXsKcmV0dXJuICJOaXJha2giOwp9",
"language": "JAVA8"
}'
```