FROM amazoncorretto:11-alpine-jdk

RUN mkdir -p /app/source
COPY . /app/source
WORKDIR /app/source
RUN ./gradlew bootJar

COPY build/libs/*.jar user-data-0.0.1-SNAPSHOT.jar
ADD build/libs/*.jar user-data-0.0.1-SNAPSHOT.jar
WORKDIR .
EXPOSE 9090
ENTRYPOINT [ "java", "-jar", "-Dspring.profiles.active=prod", "user-data-0.0.1-SNAPSHOT.jar"]
