FROM gradle:8.5-jdk21 AS builder

COPY settings.gradle /home/gradle
COPY . /app

RUN gradle build --no-daemon

FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=builder /app/build/libs/demo-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
