FROM gradle:7.3.3-jdk11 as build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN ./gradlew bootJar -DskipTests


FROM openjdk:11
EXPOSE 8081
RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/tempo-assignment-0.0.1-SNAPSHOT.jar /app/tempo-0.0.1-SNAPSHOT.jar
WORKDIR /app
CMD "java" "-jar" "tempo-0.0.1-SNAPSHOT.jar"