FROM openjdk:17-slim-buster
WORKDIR /app/
COPY ./target/scala-2.13/traveltransformer_2.13-0.1.0-SNAPSHOT.jar .

CMD ["java","-jar","traveltransformer_2.13-0.1.0-SNAPSHOT.jar"]